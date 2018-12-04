package com.communityratesgames.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.communityratesgames.domain.Rating;

public class RatingModel implements Serializable {

    private Long id;
    private String user;
    private String game;
    private int rating;
    private Timestamp creationDate;


    public RatingModel(Rating rating) {
        this.id = rating.getId();
        this.user = rating.getUser().getUserName();
        this.game = rating.getGame().getTitle();
        this.rating = rating.getRating();
        this.creationDate = rating.getCreationDate();
    }

    public RatingModel(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "RatingModel{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", game='" + game + '\'' +
                ", rating=" + rating +
                ", creationDate=" + creationDate +
                '}';
    }
}
