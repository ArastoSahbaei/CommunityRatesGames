package com.communityratesgames.game;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class GameService implements GameServiceInterface {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    private List<GameModel> convertEntityListToModelList(List<GameEntity> list) {
        List<GameModel> out = new ArrayList<>();
        for (GameEntity entity : list) {
            out.add(new GameModel(entity));
        }
        return out;
    }

    @Override
    public GameModel createGame(GameModel gameModel) {
        GameEntity gameEntity = new GameEntity(gameModel);
        return new GameModel(gameRepository.save(gameEntity));
    }

    public List<String> searchGame(String searchString){
        return gameRepository.findFirst5ByTitle(searchString, Sort.unsorted()).stream().map(GameEntity::getTitle).collect((Collectors.toList()));

    }

    @Override
    public List<GameModel> findAllGames() {
        return convertEntityListToModelList(gameRepository.findAll());
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
