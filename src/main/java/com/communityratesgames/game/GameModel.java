package com.communityratesgames.game;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import com.communityratesgames.platform.PlatformEntity;
import com.communityratesgames.platform.PlatformModel;
import com.communityratesgames.company.CompanyEntity;
import com.communityratesgames.company.CompanyModel;

public class GameModel implements Serializable {

    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }
}
