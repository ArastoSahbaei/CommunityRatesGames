package com.communityratesgames.rating;

import java.io.Serializable;
import java.sql.Timestamp;

import com.communityratesgames.game.GameModel;
import com.communityratesgames.user.UserModel;

public class RatingModel implements Serializable {

    private Long id;
    private UserModel user;
    private GameModel game;
    private int rating;
    private Timestamp creationDate;


    public RatingModel(RatingEntity ratingEntity) {
        this.id = ratingEntity.getId();
        this.user = new UserModel(ratingEntity.getUser());
        this.game = new GameModel(ratingEntity.getGame());
        this.rating = ratingEntity.getRating();
        this.creationDate = ratingEntity.getCreationDate();
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
