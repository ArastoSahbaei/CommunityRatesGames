package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Rating;
import com.communityratesgames.model.RatingModel;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }
    @GET
    @Path("/bygame")
    @Produces({"application/JSON"})
    public Response findRatingsByGameId(@QueryParam("title") String title) {
        try {
            List<RatingModel> result = dal.findRatingsByGameId(title);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }
    @GET
    @Path("/average")
    @Produces({"application/JSON"})
    public Response getAverageOfGame(@QueryParam("title") String gameTitle) {
        try {
            float result = dal.getAverageOfGame(gameTitle);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/one")
    @Produces({"application/JSON"})
    public Response findByGameIdAndUserId(@QueryParam("title") String gameTitle, @QueryParam("user") String username) {
        try {
            RatingModel result = dal.findByGameIdAndUserId(gameTitle, username);
            return Response.ok(result).build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/new")
    @Consumes({"application/JSON"})
    public Response createNewRating(RatingModel rating) {
        try {
            dal.addNewRating(rating);
            return Response.ok("rating added").build();
        } catch ( Exception e ) {
            return Response.status(404).build();
        }
    }
}
