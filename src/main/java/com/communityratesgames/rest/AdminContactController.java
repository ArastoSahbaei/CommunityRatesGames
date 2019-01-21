package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.AdminContact;
import com.communityratesgames.domain.User;
import com.communityratesgames.model.AdminContactModel;
import com.communityratesgames.util.AuthUtils;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/admincontact")
public class AdminContactController {
    @Inject
    private DataAccessLocal dal;

    @GET
    @Path("/adminall")
    @Produces({"application/JSON"})
    public Response adminGetAll() {
        try {
            List<AdminContact> result = dal.adminGetAllMessages();
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/adminone")
    @Produces({"application/JSON"})
    public Response adminGetOne(
            @QueryParam("id") Long id,
            @Context HttpHeaders header) {
        try {
            Long token = AuthUtils.getHeaderToken(header);
            if (token != null){
                if (hasAuthorization(token,"Admin")) {
                    AdminContact result = dal.adminGetMessage(id);
                    return Response.ok(result).build();
                }
                return Response.status(Response.Status.FORBIDDEN).entity("{\"Not authorized for this action\"}").build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"invalid auth token\"}").build();
            }

        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @GET
    @Path("/userall")
    @Produces({"application/JSON"})
    public Response userMessages(@QueryParam("email") String email) {
        try {
            List<AdminContactModel> result = dal.userMessages(email);
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/new")
    @Produces({"application/JSON"})
    public Response newMessage(AdminContactModel model) {
        try {
            dal.newMessage(model);
            return Response.ok().build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces({"application/JSON"})
    public Response update(AdminContact entity) {
        try {
            dal.updateEntry(entity);
            return Response.ok().build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    private Response securityCheck(HttpHeaders header, String authlevel){
        Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
        Long token = AuthUtils.getHeaderToken(header);
        if(token==null){
            builder.status(Response.Status.UNAUTHORIZED);
            builder.entity("User not logged in");
        }else if(!hasAuthorization(token, authlevel)){
            builder.status(Response.Status.FORBIDDEN);
            builder.entity("User not authorized for this action");
        }else {
            return null;
        }
        return builder.build();
    }
    private boolean hasAuthorization(Long token, String authLevel) {
        return dal.getUserToken(token).getRole().equalsIgnoreCase(authLevel);

    }
}