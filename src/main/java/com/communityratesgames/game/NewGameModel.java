package com.communityratesgames.game;

import java.io.Serializable;
import java.util.List;

public class NewGameModel implements Serializable {
    
    private String title;
    private Long companyId;
    private List<Long> allPlatformId;

    public NewGameModel() {
    }

    public NewGameModel(String title, Long companyId, List<Long> allPlatformId) {
        this.title = title;
        this.companyId = companyId;
        this.allPlatformId = allPlatformId;
    }

    public String getTitle() {
        return title;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public List<Long> getAllPlatformId() {
        return allPlatformId;
    }
}
