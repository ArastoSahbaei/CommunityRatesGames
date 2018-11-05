package com.communityratesgames.Game;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer company;

   // @Column
 //   private List<String> platforms;

    public GameEntity() {}

    public GameEntity(GameModel gameModel) {
        this.id = gameModel.getId();
    }
}
