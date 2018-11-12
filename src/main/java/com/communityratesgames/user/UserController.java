package com.communityratesgames.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> resultList = userService.findAllUsers();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserModel> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity(userService.findUserByUserName(username), HttpStatus.OK);
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
    public ResponseEntity<LoginResponse> login(@RequestParam("token") Integer token) {
        for (int i = 0; i < logins.size(); i++) {
            if (logins.get(i).token == token) {
                logins.remove(i);
                return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new LoginResponse(0), HttpStatus.NOT_FOUND);
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
