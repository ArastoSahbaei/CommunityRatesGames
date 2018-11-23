package com.communityratesgames.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

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
        UserEntity user = userService.findUserByUserNameAndPassword(login.getUsername(), login.getPassword());
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
    }
}
