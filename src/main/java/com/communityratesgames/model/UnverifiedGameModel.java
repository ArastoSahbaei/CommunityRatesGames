package com.communityratesgames.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class UnverifiedGameModel implements Serializable{
        private Long id;
        private Timestamp releaseDate;
        private String title;
        private CompanyModel company;
        private List<PlatformModel> platforms;

    public UnverifiedGameModel() {}
}