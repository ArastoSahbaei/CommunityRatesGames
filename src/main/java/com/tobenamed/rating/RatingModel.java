package com.tobenamed.rating;

import java.io.Serializable;
import java.sql.Timestamp;

public class RatingModel implements Serializable {

    private Long id;
    private Long userId;
    private Long gameId;
    private int rating;
    private Timestamp creationDate;


    public RatingModel(RatingEntity ratingEntity) {
        this.id = ratingEntity.getId();
        this.userId = ratingEntity.getUserId();
        this.gameId = ratingEntity.getGameId();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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
