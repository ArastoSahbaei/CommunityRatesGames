package com.communityratesgames.user;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.math.BigInteger;

@Entity
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp userCreated;

    @Column(unique = true)
    @Size(min = 4, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
    private String userName;

    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9_]+@$")
    private String email;
    //"^[a-zA-Z0-9!#$*+-<>^~_]+$"
    private String passwordHash;
    private String password;
    private String role;

    public UserEntity(UserModel userModel) {
        this.id = userModel.getId();
        this.userName = userModel.getUserName();
        this.email = userModel.getEmail();
        this.setPassword(userModel.getPassword());
        this.role = userModel.getRole();
        this.userCreated = userModel.getUserCreated();
    }

    public UserEntity() {
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

    public void setPassword(String password) {
        // Use hashed SHA-256.
        byte[] hash = new byte[4];
        SecureRandom rand = new SecureRandom();
        rand.setSeed(rand.generateSeed(4));
        rand.nextBytes(hash);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            this.passwordHash = Integer.toHexString(((int)hash[0] << 24) | ((int)hash[1] << 16) | ((int)hash[2] << 8) | (int)hash[3]);
            password += passwordHash;
            md.update(password.getBytes(StandardCharsets.UTF_8));
            hash = md.digest();
            this.password = String.format("%064x", new BigInteger(1, hash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
