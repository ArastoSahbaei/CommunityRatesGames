package com.communityratesgames.model;

import com.communityratesgames.domain.User;
import com.communityratesgames.util.JsonError;
import lombok.ToString;
import org.apache.log4j.Logger;

import javax.json.*;
import java.io.Serializable;
import java.io.StringReader;
import java.sql.Timestamp;

@ToString
public class UserModel implements Serializable {

    private final static Logger logger = Logger.getLogger(com.communityratesgames.model.UserModel.class);

    private Long token;
    private Long id;
    private Timestamp userCreated;
    private String username;
    private String email;
    private String password;
    private String role;

    public UserModel() {
    }

    private JsonObject jsonFromString(String input) {
        JsonReader jsonReader = Json.createReader(new StringReader(input));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object;
    }

    public User toEntity(String input, boolean encryptPassword) throws JsonError {
        JsonObject json = jsonFromString(input);
        User user = new User();

        if(json.containsKey("email")) {
            email = json.getString("email", null);
            if (email == null) {
                throw new JsonError(1, "email not specified");
            }
        }

        if (json.containsKey("password")) {
            password = json.getString("password", null);
            if (password == null) {
                throw new JsonError(2, "password not specified");
            }
        }

        if (encryptPassword) {
            password = user.encryptPassword(json.getString("password"));
        }

        if (json.containsKey("username")) {
            username = json.getString("username");
            userCreated = user.getTimestamp();
            if (json.containsKey("role")) {
                role = json.getString("role");
            }
        } else {
            username = user.getUserName();
            user.setPassword(password);
            userCreated = null;
            role = null;
        }

        id = user.getId();
        user.setEmail(email);
        user.setUserName(username);
        user.setRole(role);
        user.setUserCreated(userCreated);
        user.setId(id);

        return user;
    }

    public UserModel toModel(User user, Long token) {
        UserModel um = new UserModel();

        um.username = user.getUserName();
        um.userCreated = user.getUserCreated();
        um.email = user.getEmail();
        um.role = user.getRole();
        um.token = token;

        return um;
    }

    public Long getToken() {
        return token;
    }

    public void setToke(Long token) {
        this.token = token;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
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
}
