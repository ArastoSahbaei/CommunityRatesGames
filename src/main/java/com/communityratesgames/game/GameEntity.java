package com.communityratesgames.game;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

import com.communityratesgames.platform.PlatformEntity;
import com.communityratesgames.platform.PlatformModel;
import com.communityratesgames.company.CompanyEntity;
import com.communityratesgames.company.CompanyModel;

@Entity
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @JoinColumn
    @ManyToOne
    private CompanyEntity company;

    @JoinColumn
    @ManyToMany
    private List<PlatformEntity> platforms;

    protected GameEntity() {}

    public GameEntity(GameModel gameModel) {
        this.id = gameModel.getId();
        this.title = gameModel.getTitle();
        this.company = new CompanyEntity(gameModel.getCompany());
        this.platforms = new ArrayList<PlatformEntity>();
        for (PlatformModel platform : gameModel.getPlatforms()) {
            this.platforms.add(new PlatformEntity(platform));
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public CompanyEntity getCompany() {
        return this.company;
    }

    public List<PlatformEntity> getPlatforms() {
        return this.platforms;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public void setPlatforms(List<PlatformEntity> platforms) {
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
}
