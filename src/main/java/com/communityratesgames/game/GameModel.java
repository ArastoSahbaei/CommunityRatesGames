package com.communityratesgames.game;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

import com.communityratesgames.platform.PlatformEntity;
import com.communityratesgames.platform.PlatformModel;
import com.communityratesgames.company.CompanyEntity;
import com.communityratesgames.company.CompanyModel;

public class GameModel implements Serializable {

    private Long id;
    private Timestamp releaseDate;
    private String title;
    private CompanyModel company;
    private List<PlatformModel> platforms;

    public GameModel(String title, CompanyModel company, List<PlatformModel> platforms) {
        this.title = title;
        this.company = company;
        this.platforms = platforms;
    }

    public GameModel(GameEntity gameEntity) {
        this.id = gameEntity.getId();
        this.releaseDate = gameEntity.getReleaseDate();
        this.title = gameEntity.getTitle();
        this.company = new CompanyModel(gameEntity.getCompany());
        this.platforms = new ArrayList<PlatformModel>();
        for (PlatformEntity platform : gameEntity.getPlatforms()) {
            this.platforms.add(new PlatformModel(platform));
        }
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
}
