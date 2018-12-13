package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Game;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/game")
public class GameController {

    @Inject
    private DataAccessLocal dal;

    @POST
    @Path("/create")
    @Produces({"application/JSON"})
    public Response createNewGame(Game newGame) {
        try {
            Game result = dal.createNewGame(newGame);
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/verify")
    @Produces("application/json")
    public Response verifyGame(@QueryParam("id") Long id) {
        try {
            Game result = dal.verifyGame(id);
            if (result == null) {
                // This is technically correct JSON.
                return Response.status(Status.NOT_FOUND).entity("null").build();
            }
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/all")
    @Produces({"application/JSON"})
    public Response showAllGames() {
        try {
            List<Game> result = dal.showAllGames();
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Produces({"application/JSON"})
    public Response showVerifiedGames() {
        try {
            List<Game> result = dal.showVerifiedGames();
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/title")
    @Produces({"application/JSON"})
    public Response getOneGamebyTitle(@QueryParam("title") String title) {
        try {
            Game result = dal.gameByTitle(title);
            if (result == null) {
                // This is technically correct JSON.
                return Response.status(Status.NOT_FOUND).entity("null").build();
            }
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }

    }


    @GET
    @Path("/id")
    @Produces({"application/JSON"})
    public Response getOneGamebyId(@QueryParam("id") Long id) {
        try {
            Game result = dal.gameById(id);
            if (result == null) {
                // This is technically correct JSON.
                return Response.status(Status.NOT_FOUND).entity("null").build();
            }
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/search")
    @Produces({"application/JSON"})
    public Response searchFiveGames(@QueryParam("q") String q) {
        try {
            String result = dal.searchFiveGames(q);
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/toprated")
    @Produces({"application/JSON"})
    public Response getTopRatedGames(
            @QueryParam("limit") Integer limit,
            @QueryParam("page") Integer page) {
        try {
            List<Game> topRatedGames = dal.getTopRatedGames(limit,page);
            return Response.ok(topRatedGames).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }

    }
}
