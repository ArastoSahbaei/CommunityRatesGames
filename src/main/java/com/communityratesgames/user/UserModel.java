package com.communityratesgames.user;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

public class UserModel implements Serializable {

    private Long id;
    private Timestamp userCreated;
    @Size(min = 4, max = 30)
    @Pattern(message="Username may only be alpha numeric or contain - and _" ,regexp = "^[a-zA-Z0-9-_]+$")
    private String userName;
    @Pattern(message = "Not a valid email",regexp = "^[a-zA-Z0-9_]+@$")
    private String email;
    @Size(min = 8, max = 30)
    @Pattern(message = "Password may only be alpha numeric or contain !#$*+-<>^~_", regexp = "^[a-zA-Z0-9!#$*+-<>^~_]+$")
    private String password;
    private String role;

    public UserModel(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.userName = userEntity.getUserName();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.role = userEntity.getRole();
        this.userCreated = userEntity.getUserCreated();
    }

    public UserModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Timestamp userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
