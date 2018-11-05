package com.communityratesgames.Game;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService implements GameServiceInterface {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameModel createGame(GameModel gameModel) {
        GameEntity gameEntity = new GameEntity(gameModel);
        return new GameModel(gameRepository.save(gameEntity));
    }

    @Override
    public List<GameModel> findAllGames() {
        return null;
    }

    public GameModel findGameById(Long id) {
        GameEntity gameEntity = gameRepository.getOne(id);
        return new GameModel(gameEntity);
    }

    @Override
    public GameEntity findGameByTitle(String title) {
        return null;
    }
}
