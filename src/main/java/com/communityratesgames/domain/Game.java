package com.communityratesgames.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "game_entity")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp releaseDate;
    @Column
    private String title;
    @ManyToOne
    @JoinColumn
    private Company company;
    @Column(nullable = false)
    private boolean verified = false;
    @Column(name = "average_rating")
    private Float averageRating;

    @JoinTable(name = "game_platform",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "platform_id")}
    )
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Platform> platforms;

    public Game() {
    }
/*

    public Game(GameModel gameModel) {
        this.id = gameModel.getId();
        this.releaseDate = gameModel.getReleaseDate();
        this.title = gameModel.getTitle();
        this.company = new Company(gameModel.getCompany());
        this.platforms = new ArrayList<Platform>();
        for (PlatformModel platform : gameModel.getPlatforms()) {
            this.platforms.add(new Platform(platform));
        }
        this.averageRating = gameModel.getAverageRating();
        this.verified = false;
    }
    */

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Company getCompany() {
        return this.company;
    }

    public List<Platform> getPlatforms() {
        return this.platforms;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }
}
