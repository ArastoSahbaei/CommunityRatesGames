package com.communityratesgames.domain;

import com.communityratesgames.model.AdminContactModel;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;


@Entity
@ToString
@Table(name = "admincontact_entity")
public class AdminContact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp creationDate;
    private String email;
    private String administredBy;
    private String userMessage;
    private String responseMessage;
    private String flaggedForAdmin;
    private boolean seen;
    private boolean urgent;

    public AdminContact() {
    }

    public AdminContact(AdminContactModel model) {
        this.creationDate = model.getCreationDate()==null ? Timestamp.from(Instant.now()) : model.getCreationDate();
        this.email = model.getEmail();
        this.administredBy = model.getAdministredBy();
        this.userMessage = model.getUserMessage();
        this.responseMessage = model.getResponseMessage();
        this.seen = model.isSeen();
    }

    public AdminContact(Timestamp creationDate, String email, String admin, String userMessage, String responseMessage, String flaggedForAdmin, boolean seen, boolean urgent) {
        this.creationDate = creationDate==null ? Timestamp.from(Instant.now()) : creationDate;
        this.email = email;
        this.administredBy = admin;
        this.userMessage = userMessage;
        this.seen = seen;
        this.responseMessage = responseMessage;
        this.flaggedForAdmin = flaggedForAdmin;
        this.urgent = urgent;
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

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getFlaggedForAdmin() {
        return flaggedForAdmin;
    }

    public void setFlaggedForAdmin(String flaggedForAdmin) {
        this.flaggedForAdmin = flaggedForAdmin;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }
}
