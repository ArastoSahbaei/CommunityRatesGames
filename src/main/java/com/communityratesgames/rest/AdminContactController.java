package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.AdminContact;
import com.communityratesgames.domain.UserRole;
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
    public Response adminGetAll(@Context HttpHeaders header) {
        try {
            if (securityCheck(header,UserRole.ADMIN)) {
                List<AdminContact> result = dal.adminGetAllMessages();
                return Response.ok(result).build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
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
            if(securityCheck(header,UserRole.ADMIN)){
                AdminContact result = dal.adminGetMessage(id);
                return Response.ok(result).build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/userall")
    @Produces({"application/JSON"})
    public Response userMessages(
            @QueryParam("email") String email,
            @Context HttpHeaders header) {
        try {
            if (securityCheck(header,UserRole.USER)){
                List<AdminContactModel> result = dal.userMessages(email);
                return Response.ok(result).build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/new")
    @Produces({"application/JSON"})
    public Response newMessage(
            AdminContactModel model,
            @Context HttpHeaders header) {
        try {
            if (securityCheck(header,UserRole.USER)){
                dal.newMessage(model);
                return Response.ok().build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/update")
    @Produces({"application/JSON"})
    public Response update(
            AdminContact entity,
            @Context HttpHeaders header) {
        try {
            if (securityCheck(header, UserRole.ADMIN)){
                dal.updateEntry(entity);
                return Response.ok().build();
            }else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
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