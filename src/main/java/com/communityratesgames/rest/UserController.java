package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.User;
import com.communityratesgames.util.json.*;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.text.ParseException;

@NoArgsConstructor
@Stateless
@Path("/user")
public class UserController {

    private final static Logger logger = Logger.getLogger(com.communityratesgames.rest.UserController.class);

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
            JsonObject object = new JsonObject(credentials);
            String uname = object.getString("username");
            String email = object.getString("email");
            String password = object.getString("password");

            User user = new User(uname, email, password);
            dal.register(user);
            return Response.ok(user).build();
        } catch (ParseException | IOException e) {
            return Response.status(413).entity(e.getMessage()).build();
        } catch (JsonGetException e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    @Produces({"application/json"})
    @Consumes({"application/JSON"})
    public Response login(String credentials) {
        try {
            JsonObject object = new JsonObject(credentials);
            String email = object.getString("email");
            String password = object.getString("password");

            User u = dal.login(email, password);
            if (u == null) {
                return Response.status(417).entity("{\"error\":\"Invalid username and/or password.\"}").build();
            }

            try {
                String temp = new JsonObject()
                    .append("username", new JsonString(u.getUserName()))
                    .append("registerDate", new JsonString(u.getUserCreated().toString()))
                    .build();
                //String temp = mapper.writeValueAsString(u);
                return Response.ok(temp).build();
            } catch (IOException e) {
                e.printStackTrace();
                return Response.status(500).build();
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return Response.status(500).build();
        } catch (JsonGetException e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
    /*

    private static int loginIndex = 0;
    private static List<AuthToken> logins = new ArrayList<AuthToken>();

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> RegisterNewUser(@RequestBody UserModel userModel) {
        return userService.validateUserConstraints(userModel);
    }

    @GetMapping("/credential")
    public ResponseEntity<?> checkCredentials(@RequestBody UserModel userModel) {
        System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return userService.checkCredentials(userModel);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUserById(
            @RequestParam(value="token",required=false) Long token,
            @RequestParam(value="id", required=false) Long id) {
        if (token != null) {
            id = AuthToken.getUserId(token);
            if (id == -1) {
                return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
            }
        }
        if (id == null) {
            return new ResponseEntity<>("no id given", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Account(userService.findUserById(id)), HttpStatus.OK);
    }

    @GetMapping("/user/byname")
    public ResponseEntity<Account> getUserByUsername(@RequestParam("name") String username) {
        UserModel user = userService.findUserByUserName(username);
        return new ResponseEntity<>(new Account(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginModel login) {
        User user = userService.findUserByUserNameAndPassword(login.getUsername(), login.getPassword());
        if (user == null) {
            return new ResponseEntity<>(new LoginResponse(0L), HttpStatus.I_AM_A_TEAPOT);
        }

        Long token = AuthToken.generateNewToken(user.getId());
        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(@RequestParam("token") Long token) {
        if (!AuthToken.close(token)) {
            return new ResponseEntity<>(new LoginResponse(0L), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
        }
    }
}

class Account {
    public Long id;
    public Timestamp userCreated;
    public String userName;

    public Account(UserModel model) {
        this.id = model.getId();
        this.userCreated = model.getUserCreated();
        this.userName = model.getUserName();
    }*/
}
