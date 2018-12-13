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
            return Response.ok(toModel).build();
        } catch (JsonError e) {
            return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
        } catch (PersistenceException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/logout")
    @Produces({"application/json"})
    @Consumes({"application/JSON"})
    public Response logout(@QueryParam("token") Long token) {
        try {
            if (dal.logout(token)) {
                return Response.status(Status.OK).build();
            } else {
                return Response.status(Status.BAD_REQUEST).build();
            }
        } catch (PersistenceException e) {
            return Response.status(Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}
