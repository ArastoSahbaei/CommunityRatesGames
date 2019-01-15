package com.communityratesgames.model;

import com.communityratesgames.domain.AdminContact;

import java.sql.Timestamp;

public class AdminContactModel {
    private Long id;
    private Timestamp creationDate;
    private String email;
    private String admin;
    private String userMessage;
    private String responseMessage;
    private boolean read;

    public AdminContactModel() {
    }

    public AdminContactModel(AdminContact entity) {
        this.id = entity.getId();
        this.creationDate = entity.getCreationDate();
        this.email = entity.getEmail();
        this.admin = entity.getAdmin();
        this.userMessage = entity.getUserMessage();
        this.responseMessage = entity.getResponseMessage();
        this.read = entity.isRead();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
