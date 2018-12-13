package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.User;
import com.communityratesgames.jms.JMSSender;
import com.communityratesgames.model.UserModel;
import com.communityratesgames.user.AuthToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
        } catch ( Exception e ) {
            return Response.status(404).build();
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
        } catch ( Exception e ) {
            return Response.status(406).entity(e.getMessage()).build();
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
                return Response.status(400).entity("invalid credentials").build();
            }
            UserModel toModel = userModel.toModel(user2);
            sender.registerLog(user2.toJMS());
            return Response.ok(toModel).build();
        } catch ( Exception e ) {
            return Response.status(401).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/logout")
    @Produces({"application/json"})
    @Consumes({"application/JSON"})
    public Response logout(@QueryParam("token") Long token) {
        try {
            if (dal.logout(token)) {
                return Response.status(200).build();
            } else {
                return Response.status(400).build();
            }
        } catch ( Exception e ) {
            return Response.status(401).entity(e.getMessage()).build();
        }
    }
}
