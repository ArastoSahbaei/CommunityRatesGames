package com.communityratesgames.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.communityratesgames.domain.Game;
import com.communityratesgames.domain.Platform;
import com.communityratesgames.domain.UnverifiedGame;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameModel implements Serializable {

    private Long id;
    private Timestamp releaseDate;
    private String title;
    private String company;
    private Float averageRating;
    private List<String> platforms;
    private String description;

    public GameModel toModel(String input) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> list = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(input);
            GameModel gameModel = new GameModel();
            gameModel.setReleaseDate(Timestamp.valueOf(root.get("releaseDate").asText()));
            gameModel.setTitle(root.get("title").asText());
            gameModel.setCompany(root.get("company").asText());
            gameModel.setAverageRating(root.get("averageRating").floatValue());
            JsonNode jn = root.get("platforms");
            for (JsonNode n : jn
            ) {
                list.add(n.asText());
            }
            gameModel.setPlatforms(list);


            return gameModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public GameModel(Game entity) {
        this.id = entity.getId();
        this.releaseDate = entity.getReleaseDate();
        this.title = entity.getTitle();
        this.company = entity.getCompany().getCompanyName();
        this.platforms = entity.getPlatforms().stream().map(Platform::getName).collect(Collectors.toList());
        this.description = entity.getDescription();
        this.averageRating = entity.getAverageRating();
    }

    public GameModel(UnverifiedGame entity) {
        this(new Game(entity,true));
    }

    public GameModel() {
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}