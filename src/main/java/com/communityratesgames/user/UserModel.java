package com.communityratesgames.user;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserModel implements Serializable {

    private Long id;
    private Timestamp userCreated;
    private String userName;
    private String email;
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
