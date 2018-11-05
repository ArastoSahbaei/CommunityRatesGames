package com.communityratesgames.Game;

import java.util.List;

public interface GameServiceInterface {

    List<GameModel> findAllGames();
    GameModel findGameById(Long id);
    GameModel createGame(GameModel gameModel);
    GameEntity findGameByTitle(String title);
}
