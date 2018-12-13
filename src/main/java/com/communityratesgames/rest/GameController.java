package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Game;
import com.communityratesgames.model.GameModel;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
    public Response createNewGame(GameModel newGame) {
        try {
            dal.createNewGame(newGame);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(414).build();
        }
    }

    @GET
    @Path("/all")
    @Produces({"application/JSON"})
    public Response showAllGames() {
        try {
            List<GameModel> result = dal.showAllGames();
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(414).build();
        }
    }

    @GET
    @Path("/title")
    @Produces({"application/JSON"})
    public Response getOneGamebyTitle(@QueryParam("title") String title) {
        try {
            GameModel result = dal.gameByTitle(title);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(414).build();

        }
    }

    @GET
    @Path("/id")
    @Produces({"application/JSON"})
    public Response getOneGamebyId(@QueryParam("id") Long id) {
        try {
            GameModel result = dal.gameById(id);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(414).build();
        }
    }

    @GET
    @Path("/search")
    @Produces({"application/JSON"})
    public Response searchFiveGames(@QueryParam("q") String q) {
        try {
            String result = dal.searchFiveGames(q);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(414).build();
        }
    }

    @GET
    @Path("/toprated")
    @Produces({"application/JSON"})
    public Response getTopRatedGames(
            @QueryParam("limit") Integer limit,
            @QueryParam("page") Integer page) {
        try {
            List<GameModel> topRatedGames = dal.getTopRatedGames(limit,page);
            return Response.ok(topRatedGames).build();
        } catch (Exception e) {
            return Response.status(414).build();
        }

    }
}
