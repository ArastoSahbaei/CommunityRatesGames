package com.communityratesgames.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

import com.communityratesgames.model.GameModel;
import com.communityratesgames.model.PlatformModel;
import org.picketlink.idm.model.annotation.Unique;

@Entity
@Table(name = "game_entity")

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "searchForFiveGamesByTitle",
                procedureName = "SEARCH_FIVE_GAMES",
                resultClasses = {Game.class},
                parameters = {
                        @StoredProcedureParameter(
                                name = "query",
                                type = String.class,
                                mode = ParameterMode.IN
                        )
                }
        )
})

public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp releaseDate;

    @Column
    @Unique
    private String title;

    @JoinColumn
    @ManyToOne
    private Company company;

    @JoinTable(name="game_platform",
        joinColumns={ @JoinColumn(name="game_id") },
        inverseJoinColumns={ @JoinColumn(name="platform_id") }
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Platform> platforms;

    @Column(name="average_rating")
    private Float averageRating;

    public Game() {}

    public Game(UnverifiedGame unverifiedGame, boolean withId) {
        if (withId){this.id = unverifiedGame.getId();}
        this.releaseDate = unverifiedGame.getReleaseDate();
        this.title = unverifiedGame.getTitle();
        this.company = unverifiedGame.getCompany();
        this.platforms = unverifiedGame.getPlatforms();
        this.averageRating = 0f;
    }

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
