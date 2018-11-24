package com.communityratesgames.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;

import com.communityratesgames.model.RatingModel;

@Entity
@XmlRootElement
public class RatingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private UserEntity user;

    @JoinColumn
    @ManyToOne
    private GameEntity game;
    private int rating;
    private Timestamp creationDate;

    public RatingEntity(RatingModel ratingModel) {
        this.id = ratingModel.getId();
        this.user = new UserEntity(ratingModel.getUser());
        this.game = new GameEntity(ratingModel.getGame());
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
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
