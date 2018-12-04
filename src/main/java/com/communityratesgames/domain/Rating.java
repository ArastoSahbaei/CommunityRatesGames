package com.communityratesgames.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

import com.communityratesgames.model.RatingModel;
import com.communityratesgames.transactions.RatingService;

@Entity
@Table(name = "rating_entity")
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @ManyToOne
    private Game game;
    private int rating;
    private Timestamp creationDate;

    /*
    public Rating(RatingModel model) {
        this.rating = model.getRating();
        if(model.getCreationDate() != null) {
            this.creationDate = Timestamp.from(Instant.now());
        }else {
            this.creationDate = model.getCreationDate();
        }

    }*/

    public Rating(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
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
