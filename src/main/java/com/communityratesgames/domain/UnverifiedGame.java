package com.communityratesgames.domain;

import com.communityratesgames.model.GameModel;
import com.communityratesgames.model.PlatformModel;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unverified_game_entity")
public class UnverifiedGame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Timestamp releaseDate;

    @Column
    private String title;

    @JoinColumn
    @ManyToOne
    private Company company;

    @Column(nullable=false)
    @JoinTable(name="unverified_game_platform",
        joinColumns={ @JoinColumn(name="unverified_game_id") },
        inverseJoinColumns={ @JoinColumn(name="platform_id") }
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Platform> platforms;

    public UnverifiedGame() {}

    public UnverifiedGame(Timestamp releaseDate, String title, Company company, List<Platform> platforms) {
        this.releaseDate = releaseDate;
        this.title = title;
        this.company = company;
        this.platforms = platforms;
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
}
