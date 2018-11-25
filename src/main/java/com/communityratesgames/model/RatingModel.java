package com.communityratesgames.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.communityratesgames.domain.Rating;

public class RatingModel implements Serializable {

    private Long id;
    private UserModel user;
    private GameModel game;
    private int rating;
    private Timestamp creationDate;


    public RatingModel(Rating rating) {
        this.id = rating.getId();
        this.user = new UserModel(rating.getUser());
        this.game = new GameModel(rating.getGame());
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public GameModel getGame() {
        return game;
    }

    public void setGame(GameModel game) {
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
}
