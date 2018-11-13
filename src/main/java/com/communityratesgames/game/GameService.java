package com.communityratesgames.game;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.communityratesgames.rating.RatingRepository;

@Service
public class GameService implements GameServiceInterface {

    private final GameRepository gameRepository;
    private final RatingRepository ratingRepository;

    public GameService(GameRepository gameRepository, RatingRepository ratingRepository) {
        this.gameRepository = gameRepository;
        this.ratingRepository = ratingRepository;
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

    public List<GameModel> searchGame(String searchString){
        return convertEntityListToModelList(gameRepository.findFirst5ByTitleContaining(searchString,
            Sort.unsorted()));

    }

    @Override
    public List<GameModel> findAllGames() {
        return convertEntityListToModelList(gameRepository.findAll());
    }

    public GameModel findGameById(Long id) {
        GameEntity gameEntity = gameRepository.findGameById(id);
        Float average = ratingRepository.getGameAverageRating(gameEntity.getId());
        return new GameModel(gameEntity, average);
    }

    @Override
    public GameEntity findGameByTitle(String title) {
        return null;
    }
}
