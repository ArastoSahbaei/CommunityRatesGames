package com.communityratesgames.model;

import com.communityratesgames.domain.User;
import lombok.*;

import javax.json.*;
import java.io.Serializable;
import java.io.StringReader;
import java.sql.Timestamp;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

        email = json.getString("email");
        password = json.getString("password");

        if (json.containsKey("username")) {
            username = json.getString("username");
            password = user.encryptPassword(json.getString("password"));
            userCreated = user.getTimestamp();
        } else {
            username = user.getUserName();
            user.setPassword(password);
            userCreated = null;
        }

        id = user.getId();
        role = user.getRole();


        user.setEmail(email);
        user.setUserName(username);
        user.setRole(role);
        user.setUserCreated(userCreated);
        user.setId(id);

        return user;
    }

    public UserModel toModel(User user) {
        UserModel um = new UserModel();

        um.username = user.getUserName();
        um.userCreated = user.getUserCreated();
        um.email = user.getEmail();
        um.role = user.getRole();

        return um;
    }

    public UserModel(User user) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.userCreated = user.getUserCreated();
    }
}
