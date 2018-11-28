package com.communityratesgames.domain;
import com.communityratesgames.model.UserModel;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.math.BigInteger;

@ToString
@Entity
@Table(name = "user_entity")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp userCreated;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    private String password;
    private String role;

    public User(UserModel userModel) {
        this.id = userModel.getId();
        this.userName = userModel.getUserName();
        this.email = userModel.getEmail();
        this.setPassword(userModel.getPassword());
        this.role = userModel.getRole();
        this.userCreated = userModel.getUserCreated();
    }

    public User() {
    }

    public User(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
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

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public String getPassword() {
        return password;
    }

    public static String hashPassword(String password, String hash) {
        try {
            // Use hashed SHA-256.
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            password += hash;
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] data = md.digest();
            return String.format("%064x", new BigInteger(1, data));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setPassword(String password) {
        byte[] hash = new byte[4];
        SecureRandom rand = new SecureRandom();
        rand.setSeed(rand.generateSeed(4));
        rand.nextBytes(hash);
        this.passwordHash = Integer.toHexString(
                ((int)hash[0] << 24) |
                ((int)hash[1] << 16) |
                ((int)hash[2] << 8) |
                (int)hash[3]);

        this.password = hashPassword(password, this.passwordHash);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
