package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.User;
import com.communityratesgames.jms.JMSSender;
import com.communityratesgames.model.UserModel;
import com.communityratesgames.user.AuthToken;
import com.communityratesgames.util.AuthUtils;
import com.communityratesgames.util.FileLimitReachedException;
import com.communityratesgames.util.InvalidFileFormatException;
import com.communityratesgames.util.JsonError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import static javax.ws.rs.core.Response.Status;
import java.io.IOException;
import java.util.List;
import java.util.UnknownFormatConversionException;

@NoArgsConstructor
@Stateless
@Path("/user")
public class UserController {

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
            Long token = dal.login(toEntity);
            if (token == null) {
                return Response.status(Status.NOT_FOUND).entity("{\"error\":\"invalid username and/or password\"}").build();
            }

            User u = dal.getUserToken(token);
            if (u == null) {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"corrupted token in database\"}").build();
            }
            UserModel toModel = userModel.toModel(u);
            sender.registerLog(u.toJMS());
            System.out.println(toModel.toString());
            return Response.ok(toModel).header("Authorization", token.toString()).header("Access-Control-Expose-Headers", "Authorization").build();
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
    public Response logout(@Context HttpHeaders header) {
        Long token = AuthUtils.getHeaderToken(header);
        if (token == null) {
            return Response.status(Status.UNAUTHORIZED).entity("{\"error\":\"invalid auth token\"}").build();
        }

        if (!AuthToken.close(token)) {
            return Response.status(Status.UNAUTHORIZED).entity("{\"error\":\"invalid auth token\"}").build();
        }
        return Response.status(Status.OK).build();
    }

    @GET
    @Path("/active")
    @Produces({"application/JSON"})
    public Response activeUser(@Context HttpHeaders header) {
        try {
            Long token = AuthUtils.getHeaderToken(header);
            if (token == null) {
                return Response.status(Status.UNAUTHORIZED).entity("{\"error\":\"invalid auth token\"}").build();
            }

            User u = dal.getUserToken(token);
            if (u == null) {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"corrupted token in database\"}").build();
            }

            UserModel model = userModel.toModel(u);
            return Response.ok(model).build();
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

    @POST
    @Path("/avatar")
    @Produces({"application/JSON"})
    @Consumes({"image/png", "image/jpeg", "image/tiff"})
    public Response setAvatar(@Context HttpHeaders header, byte[] imagedata) {
        Long token = AuthUtils.getHeaderToken(header);
        if (token == null) {
            return Response.status(Status.UNAUTHORIZED).entity("{\"error\":\"invalid auth token\"}").build();
        }

        User u = dal.getUserToken(token);
        try {
            u = dal.setUserAvatar(u, imagedata);
            return Response.status(Status.OK).build();
        } catch (FileLimitReachedException e) {
            return Response.status(Status.BAD_REQUEST).entity("{\"error\":\"image exceeds file size limit\"}").build();
        } catch (InvalidFileFormatException e) {
            return Response.status(Status.BAD_REQUEST).entity("{\"error\":\"image invalid; only png, jpeg and tiff are supported\"}").build();
        } catch (IOException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PUT
    @Path("/update")
    @Produces({"application/JSON"})
    @Consumes({"application/JSON"})
    public Response update(@Context HttpHeaders header, String user) {
        Long token = AuthUtils.getHeaderToken(header);
        if (token == null) {
            return Response.status(Status.UNAUTHORIZED).entity("{\"error\":\"invalid auth token\"}").build();
        }

        User u = dal.getUserToken(token);
        if (u.getRole() != "Admin") {
            return Response.status(Status.FORBIDDEN).entity("{\"error\":\"/update may only be used by admin users\"}").build();
        }

        try {
            User um = userModel.toEntity(user, false);
            System.out.println(um);
            Integer answer = dal.updateAUser(um);
            return Response.status(Status.OK).build();
        } catch( PersistenceException e ) {
            return Response.status(Status.BAD_REQUEST).build();
        } catch ( JsonError e) {
            return Response.status(Status.BAD_GATEWAY).entity(e.toString()).build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces({"application/json"})
    @Consumes({"application/JSON"})
    public Response delete(@Context HttpHeaders header, String username) {
        Long token = AuthUtils.getHeaderToken(header);
        if (token == null) {
            return Response.status(Status.UNAUTHORIZED).entity("{\"error\":\"invalid auth token\"}").build();
        }

        try {
            User u = dal.getUserToken(token);
            if (u.getRole() != "Admin") {
                return Response.status(Status.FORBIDDEN).entity("{\"error\":\"/delete may only be used by admin users\"}").build();
            }

            User um = userModel.toEntity(username, false);
            Boolean answer = dal.deleteAUser(um);
            if ( answer == true ) {
                return Response.status(Status.OK).build();
            } else {
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch( PersistenceException e ) {
            return Response.status(Status.BAD_REQUEST).build();
        } catch ( JsonError e) {
            return Response.status(Status.BAD_GATEWAY).entity(e.toString()).build();
        }
    }
}
