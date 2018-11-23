
package com.communityratesgames.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.domain.PlatformEntity;
import com.communityratesgames.model.GameModel;
import com.communityratesgames.domain.GameEntity;

public class PlatformModel implements Serializable {
    private Integer id;
    private String name;
    private int releaseYear;
    private CompanyModel company;
    private List<GameModel> games;

    public PlatformModel(PlatformEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.releaseYear = entity.getReleaseYear();
        this.company = new CompanyModel(entity.getCompany());
        this.games = new ArrayList();
        for (GameEntity game : entity.getGames()) {
            this.games.add(new GameModel(game));
        }
    }

    protected PlatformModel() { }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public CompanyModel getCompany() {
        return this.company;
    }

    public List<GameModel> getGames() {
        return this.games;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

    public void setGames(List<GameModel> games) {
        this.games = games;
    }
}
