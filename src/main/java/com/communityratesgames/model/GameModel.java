package com.communityratesgames.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

import com.communityratesgames.domain.Game;
import com.communityratesgames.domain.Platform;

public class GameModel implements Serializable {

    private Long id;
    private Timestamp releaseDate;
    private String title;
    private CompanyModel company;
    private List<PlatformModel> platforms;
    private float averageRating;

    public GameModel(String title, CompanyModel company, List<PlatformModel> platforms) {
        this.title = title;
        this.company = company;
        this.platforms = platforms;
    }

    public GameModel(Game game) {
        this(game, 0.0f);
    }

    public GameModel(Game game, float average) {
        this.id = game.getId();
        this.releaseDate = game.getReleaseDate();
        this.title = game.getTitle();
        this.company = new CompanyModel(game.getCompany());
        this.platforms = new ArrayList<PlatformModel>();
        for (Platform platform : game.getPlatforms()) {
            this.platforms.add(new PlatformModel(platform));
        }
        this.averageRating = average;
    }

    protected GameModel() { }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public CompanyModel getCompany() {
        return this.company;
    }

    public List<PlatformModel> getPlatforms() {
        return this.platforms;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

    public void setPlatforms(List<PlatformModel> platforms) {
        this.platforms = platforms;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getAverageRating() {
        return this.averageRating;
    }

    public void setAverageRating(float average) {
        this.averageRating = average;
    }
}
