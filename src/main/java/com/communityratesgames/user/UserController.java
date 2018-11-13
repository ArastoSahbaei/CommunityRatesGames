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

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> RegisterNewUser(@RequestBody UserModel userModel) {
        return userService.validateUserConstraints(userModel);
    }

    @GetMapping("/user")
    public ResponseEntity<Account> getUserById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(new Account(userService.findUserById(id)), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Account> getUserByUsername(@PathVariable String username) {
        UserModel user = userService.findUserByUserName(username);
        return new ResponseEntity(new Account(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginModel login) {
        UserEntity user = userService.findUserByUserNameAndPassword(login.getUsername(), login.getPassword());
        if (user == null) {
            return new ResponseEntity<>(new LoginResponse(0), HttpStatus.I_AM_A_TEAPOT);
        }

        loginIndex++;
        logins.add(new AuthToken((Integer)loginIndex, user.getId()));
        return new ResponseEntity<>(new LoginResponse(loginIndex), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(@RequestParam("token") Integer token) {
        for (int i = 0; i < logins.size(); i++) {
            if (logins.get(i).token == token) {
                logins.remove(i);
                return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new LoginResponse(0), HttpStatus.NOT_FOUND);
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

class AuthToken {
    public Integer token;
    public Long userId;

    public AuthToken(Integer token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
}
