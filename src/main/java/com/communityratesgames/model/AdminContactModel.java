package com.communityratesgames.model;

import com.communityratesgames.domain.AdminContact;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdminContactModel implements Serializable {
    private Long id;
    private Timestamp creationDate;
    private String email;
    private String administredBy;
    private String userMessage;
    private String responseMessage;
    private boolean seen;

    public AdminContactModel() {
    }

    public AdminContactModel(AdminContact entity) {
        this.id = entity.getId();
        this.creationDate = entity.getCreationDate();
        this.email = entity.getEmail();
        this.administredBy = entity.getAdministredBy();
        this.userMessage = entity.getUserMessage();
        this.responseMessage = entity.getResponseMessage();
        this.seen = entity.isSeen();
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

    public String getAdministredBy() {
        return administredBy;
    }

    public void setAdministredBy(String administredBy) {
        this.administredBy = administredBy;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
