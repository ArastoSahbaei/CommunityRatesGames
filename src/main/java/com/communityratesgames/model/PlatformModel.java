
package com.communityratesgames.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import com.communityratesgames.domain.Platform;
import com.communityratesgames.domain.Game;

public class PlatformModel implements Serializable {
    private Long id;
    private String name;
    private int releaseYear;
    private CompanyModel company;
    private List<GameModel> games;

    public PlatformModel(Platform entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.releaseYear = entity.getReleaseYear();
        this.company = new CompanyModel(entity.getCompany());
        this.games = new ArrayList<>();
        for (Game game : entity.getGames()) {
            this.games.add(new GameModel(game));
        }
    }

    protected PlatformModel() { }

    public Long getId() {
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
