
package com.communityratesgames.platform;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;
import java.util.ArrayList;

import com.communityratesgames.company.CompanyEntity;
import com.communityratesgames.game.GameEntity;
import com.communityratesgames.game.GameModel;

@Entity
public class PlatformEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(length=80)
    private String name;

    private int releaseYear;

    @ManyToOne
    @JoinColumn
    private CompanyEntity company;

    @ManyToMany
    private List<GameEntity> games;

    public PlatformEntity(String name, int releaseYear, CompanyEntity company, List<GameEntity> games) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.company = company;
        this.games = games;
    }

    public PlatformEntity(PlatformModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.releaseYear = model.getReleaseYear();
        this.company = new CompanyEntity(model.getCompany());
        this.games = new ArrayList<GameEntity>();
        for (GameModel game : model.getGames()) {
            this.games.add(new GameEntity(game));
        }
    }

    protected PlatformEntity() { }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public CompanyEntity getCompany() {
        return this.company;
    }

    public List<GameEntity> getGames() {
        return this.games;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
    }
}
