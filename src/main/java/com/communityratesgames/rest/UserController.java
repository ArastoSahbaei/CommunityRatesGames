package com.communityratesgames.rest;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.User;
import com.communityratesgames.model.UserModel;
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
        System.out.println("IN HERE::::::::::::::::::::::::::::::::::::::::::::::::: " + credentials);
        logger.info("Register Method:" + credentials);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(credentials);
            JsonNode uname = node.findValue("username");
            if (uname == null) {
                return Response.status(400).entity("{\"error\":\"Username not specified.\"}").build();
            }

            JsonNode email = node.findValue("email");
            if (email == null) {
                return Response.status(400).entity("{\"error\":\"Email not specified.\"}").build();
            }

            JsonNode password = node.findValue("password");
            if (password == null) {
                return Response.status(400).entity("{\"error\":\"Password not specified.\"}").build();
            }

            User user = new User(uname.asText(), email.asText(), password.asText());
            dal.register(user);
            return Response.ok(user).build();
        } catch ( Exception e ) {
            return Response.status(413).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    @Produces({"application/json"})
    @Consumes({"application/JSON"})
    public Response login(String credentials) {
        System.out.println("IN LOGIN:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: " + credentials);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(credentials);
            JsonNode email = node.findValue("email");
            if (email == null) {
                return Response.status(400).entity("{\"error\":\"Email not specified.\"}").build();
            }
            JsonNode password = node.findValue("password");
            if (password == null) {
                return Response.status(400).entity("{\"error\":\"Password not specified.\"}").build();
            }

            User u = dal.login(email.asText(), password.asText());
            String temp = mapper.writeValueAsString(u);
            return Response.ok(temp).build();
        } catch ( Exception e ) {
            return Response.status(401).entity(e.getMessage()).build();
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
