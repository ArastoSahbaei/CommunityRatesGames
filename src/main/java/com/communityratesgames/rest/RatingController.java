package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Rating;
import com.communityratesgames.domain.User;
import com.communityratesgames.model.RatingModel;
import com.communityratesgames.util.json.*;
import com.communityratesgames.util.AuthUtils;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import static javax.ws.rs.core.Response.Status;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Stateless
@Path("/rating")
public class RatingController {
    private final static Logger logger = Logger.getLogger(com.communityratesgames.rest.RatingController.class);

    @Inject
    private DataAccessLocal dal;

    @GET
    @Produces({"application/JSON"})
    public Response showAllRatings() {
        try {
            List<RatingModel> result = dal.showAllRatings();
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/bygame")
    @Produces({"application/JSON"})
    public Response findRatingsByGameTitle(@QueryParam("title") String title) {
        try {
            List<RatingModel> result = dal.findRatingsByGameId(title);
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/byuser")
    @Produces({"application/JSON"})
    public Response findRatingsByUsername(@QueryParam("user") String user) {
        try {
            List<RatingModel> result = dal.findAllUserRatings(user);
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/average")
    @Produces({"application/JSON"})
    public Response getAverageOfGame(@QueryParam("title") String gameTitle) {
        if (gameTitle == null) {
            return Response.status(Status.BAD_REQUEST).entity("{\"error\":1,\"message\":\"title is not specified\"}").build();
        }
        try {
            float result = dal.getAverageOfGame(gameTitle);
            if (result == -1.0f) {
                return Response.status(Status.NOT_FOUND).build();
            }
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/one")
    @Produces({"application/JSON"})
    public Response findByGameIdAndUserId(@QueryParam("title") String gameTitle, @QueryParam("user") String username) {
        if (gameTitle == null) {
            return Response.status(Status.BAD_REQUEST).entity("{\"error\":1,\"message\":\"title is not specified\"}").build();
        }
        try {
            RatingModel result = dal.findByGameIdAndUserId(gameTitle, username);
            if (result == null) {
                return Response.status(Status.NOT_FOUND).build();
            }
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/new")
    @Consumes({"application/JSON"})
    public Response createNewRating(@Context HttpHeaders header, String rating) {
        Long token = AuthUtils.getHeaderToken(header);
        if (header == null) {
            return Response.status(Status.UNAUTHORIZED).entity("{\"error\":\"invalid auth token\"}").build();
        }

        try {
            User u = dal.getUserToken(token);
            JsonObject json = new JsonObject(rating);
            RatingModel model = new RatingModel();
            model.setUser(u.getUserName());
            model.setGame(json.getString("game"));
            model.setRating((int)json.getNumber("rating"));
            model.setCreationDate(new Timestamp(System.currentTimeMillis()));

            model = dal.addNewRating(model);
            if (model == null) {
                return Response.status(Status.BAD_REQUEST).build();
            }
            rating = new JsonObject()
                .append("user", new JsonString(model.getUser()))
                .append("game", new JsonString(model.getGame()))
                .append("rating", new JsonNumber(model.getRating()))
                .append("creationDate", new JsonString(model.getCreationDate().toString()))
                .build();
            return Response.ok(rating).build();
        } catch (JsonGetException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
        } catch (IOException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        } catch (ParseException | PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }
}
