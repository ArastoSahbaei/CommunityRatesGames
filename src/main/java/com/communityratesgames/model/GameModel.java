package com.communityratesgames.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameModel implements Serializable {

    private Long id;
    private Timestamp releaseDate;
    private String title;
    private String company;
    private List<String> platforms;

    public GameModel toModel(String input) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> list = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(input);
            GameModel gameModel = new GameModel();
            gameModel.setReleaseDate(Timestamp.valueOf(root.get("releaseDate").asText()));
            gameModel.setTitle(root.get("title").asText());
            gameModel.setCompany(root.get("company").asText());
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

    public GameModel() {
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
}