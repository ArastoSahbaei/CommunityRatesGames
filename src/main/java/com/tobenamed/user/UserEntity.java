package com.tobenamed.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;

    public UserEntity(UserModel userModel) {
        this.id = userModel.getId();
        this.userName = userModel.getUserName();
        this.email = userModel.getEmail();
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.password = userModel.getPassword();
        this.role = userModel.getRole();
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
