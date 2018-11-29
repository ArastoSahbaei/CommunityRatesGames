package com.communityratesgames.model;

import com.communityratesgames.domain.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.Serializable;
import java.io.StringReader;
import java.sql.Timestamp;

public class UserModel implements Serializable {

    private Long id;
    private Timestamp userCreated;
    private String username;
    private String email;
    private String password;
    private String role;
    private String emailSubject;
    private String emailText;

    public JsonObject jsonFromString(String input) {
        JsonReader jsonReader = Json.createReader(new StringReader(input));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object;
    }

    public User toEntity(String input) {
        JsonObject json = jsonFromString(input);
        User user = new User();

        System.out.println("Transferred to JsonObject::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" +json);
        email = json.getString("email");
        password = json.getString("password");
/*
        if (json.isNull("username")) {
            user.setUserName("");
        } else {
            user.setUserName(json.getString("username"));
        }
*/
        id = null;
        role = null;
        userCreated = null;
        user.setPassword(password);
        user.setEmail(email);

        System.out.println("IN MODEL:::::::::::::::::::::::::::::::::::::::::::::::" + user.toString());

        return user;
    }

    public UserModel(User user) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.userCreated = user.getUserCreated();
    }

    public UserModel(String userName, String email, String password) {
        this.username = userName;
        this.email = email;
        this.password = password;
    }

    public UserModel(String emailSubject, String emailText) {
        this.emailSubject = emailSubject;
        this.emailText = emailText;
    }

    public UserModel(){}

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
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
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

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", userCreated=" + userCreated +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", emailText='" + emailText + '\'' +
                '}';
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }
}
