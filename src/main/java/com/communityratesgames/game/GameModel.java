package com.communityratesgames.game;

import java.io.Serializable;
import java.util.List;

public class GameModel implements Serializable {

    private Long id;
    private String title;
    private Integer company;
    private List<String> platforms;

    public GameModel(GameEntity gameEntity) {
        this.id = gameEntity.getId();
        this.title = gameEntity.getTitle();
        this.company = gameEntity.getCompany();
      //  this.platforms = gameEntity.getPlatforms();
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
