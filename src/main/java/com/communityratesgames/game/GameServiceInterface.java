package com.communityratesgames.game;

import java.util.List;

public interface GameServiceInterface {

    List<GameModel> findAllGames();
    GameModel findGameById(Long id);
    GameModel createGame(NewGameModel inputGame);
    GameModel findGameByTitle(String title);
}
