package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.UserRole;
import com.communityratesgames.model.GameModel;
import com.communityratesgames.util.AuthUtils;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
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
    public Response addUnverifiedGame(GameModel newGame,
                                      @Context HttpHeaders header) {
        try {
            if (securityCheck(header, UserRole.USER)){
                dal.addUnverifiedGame(newGame);
                return Response.ok("create").build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.status(400).build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces({"application/JSON"})
    public Response deleteUnverifiedGame(@QueryParam("id") Long id,
                                         @Context HttpHeaders header) {
        try {
            if (securityCheck(header, UserRole.ADMIN)){
                dal.deleteUnverifiedGame(id);
                return Response.ok().build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

        } catch (Exception e) {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/verify")
    @Produces({"application/JSON"})
    public Response verifiyGame(@QueryParam("id") Long id,
                                @Context HttpHeaders header) {
        try {
            if (securityCheck(header, UserRole.ADMIN)){
                dal.verifyGame(id);
                return Response.ok().build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.status(400).build();
        }
    }

    @GET
    @Path("/list")
    @Produces({"application/JSON"})
    public Response addUnverifiedGame(@Context HttpHeaders header) {
        try {
            if (securityCheck(header, UserRole.ADMIN)){
                List<GameModel> result = dal.getAllUnverifiedGames();
                return Response.ok(result).build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.status(400).build();
        }
    }

    private boolean securityCheck(HttpHeaders header, UserRole authlevel){
        Long token = AuthUtils.getHeaderToken(header);
        if(token==null){
            return false;
        }else return hasAuthorization(token, authlevel);
    }
    private boolean hasAuthorization(Long token, UserRole authLevel) {
        return dal.getUserToken(token).getRole().ordinal() >= authLevel.ordinal();
    }
}
