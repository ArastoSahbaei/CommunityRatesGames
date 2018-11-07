package com.communityratesgames.rating;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class RatingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Long userId;
    @ManyToOne
    private Long gameId;
    private int rating;
    private Timestamp creationDate;

    public RatingEntity(RatingModel ratingModel) {
        this.id = ratingModel.getId();
        this.userId = ratingModel.getUserId();
        this.gameId = ratingModel.getGameId();
        this.rating = ratingModel.getRating();
        this.creationDate = ratingModel.getCreationDate();
    }
    public RatingEntity(){}

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
