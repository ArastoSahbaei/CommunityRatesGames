package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.model.GameModel;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/unverifiedgame")
public class UnverifiedGameController {
    @Inject
    private DataAccessLocal dal;

    @POST
    @Path("/create")
    @Produces({"application/JSON"})
    public Response addUnverifiedGame(GameModel newGame) {
        try {
            dal.addUnverifiedGame(newGame);
            return Response.ok("create").build();
        } catch (Exception e) {
            return Response.status(414).build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces({"application/JSON"})
    public Response deleteUnverifiedGame(@QueryParam("id") Long id) {
        try {
            dal.deleteUnverifiedGame(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(414).build();
        }
    }

    @POST
    @Path("/verify")
    @Produces({"application/JSON"})
    public Response verifiyGame(@QueryParam("id") Long id) {
        try {
            dal.verifyGame(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(414).build();
        }
    }

    @GET
    @Path("/list")
    @Produces({"application/JSON"})
    public Response addUnverifiedGame() {
        try {
            List<GameModel> result = dal.getAllUnverifiedGames();
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(414).build();
        }
    }
}
