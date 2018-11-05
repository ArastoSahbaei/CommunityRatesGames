package com.communityratesgames.game;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}
