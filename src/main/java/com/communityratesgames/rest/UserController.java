package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.User;
import com.communityratesgames.jms.JMSSender;
import com.communityratesgames.model.UserModel;
import com.communityratesgames.user.AuthToken;
import com.communityratesgames.util.JsonError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status;
import java.util.List;

@NoArgsConstructor
@Stateless
@Path("/user")
public class UserController {

    private final static Logger logger = Logger.getLogger(com.communityratesgames.rest.UserController.class);
    private UserModel userModel = new UserModel();

    @Inject
    JMSSender sender;

    @Inject
    private DataAccessLocal dal;

    @GET
    @Produces({"application/json"})
    public Response showAllUsers() {
        try {
            List<User> result = dal.showAllUsers();
            return Response.ok(result).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/register")
    @Produces("application/json")
    @Consumes("application/json")
    public Response register(String credentials) {
        try {
            User toEntity = userModel.toEntity(credentials, true);
            User user2 = dal.register(toEntity);
            UserModel toModel = userModel.toModel(user2);
            return Response.ok(toModel).build();
        } catch (JsonError e) {
            return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    @Produces({"application/json"})
    @Consumes({"application/JSON"})
    public Response login(String credentials) {
        try {
            User toEntity = userModel.toEntity(credentials, false);
            User user2 = dal.login(toEntity);
            if (user2 == null) {
                return Response.status(Status.NOT_FOUND).entity("{\"error\":\"invalid username and/or password\"}").build();
            }
            UserModel toModel = userModel.toModel(user2);
            sender.registerLog(user2.toJMS());
            System.out.println(toModel.toString());
            return Response.ok(toModel).build();
        } catch (JsonError e) {
            return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/certainUser")
    @Produces({"application/JSON"})
    @Consumes({"application/JSON"})
    public Response userDetails(@QueryParam("name") String user) {
        try {
            User toEntity = dal.detailsAboutAUser(user);
            return Response.ok(toEntity).build();
        } catch ( Exception e ) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }


    @PUT
    @Path("/update")
    @Produces({"application/JSON"})
    @Consumes({"application/JSON"})
    public Response update(String user) {
        System.out.println(user);
        return null;
    }

    @DELETE
    @Path("/delete")
    @Produces({"application/json"})
    @Consumes({"application/JSON"})
    public Response logout(String user) {
        try {
            System.out.println(user);
            return null;
        } catch (PersistenceException e) {
            return Response.status(Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}
