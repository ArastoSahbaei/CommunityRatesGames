package com.communityratesgames.transactions;

import com.communityratesgames.domain.Game;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Default
public class GameService implements GameDataAccess {

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public List<Game> showAllGames() {
        Query q = em.createNativeQuery("SELECT * FROM game_entity", Game.class);
        List<Game> result = q.getResultList();
        return result;
    }

    @Override
    public Game gameByTitle(String title) {
        Query q = em.createNativeQuery("SELECT * FROM game_entity WHERE title = :title",Game.class);
        q.setParameter("title", title);
        Game game = (Game)q.getSingleResult();
        return game;
    }
/*
    public Game gameByTitle(String title){
        Query q = em.createNativeQuery("SELECT * FROM game_entity WHERE title = :title",Game.class);
        q.setParameter("title", title);
        Game game = (Game)q.getSingleResult();
        return game;
    }
/*
    private final GameRepository gameRepository;
    private final RatingRepository ratingRepository;

    public GameService(GameRepository gameRepository, RatingRepository ratingRepository) {
        this.gameRepository = gameRepository;
        this.ratingRepository = ratingRepository;
    }

    private List<GameModel> convertEntityListToModelList(List<Game> list) {
        List<GameModel> out = new ArrayList<>();
        for (Game entity : list) {
            out.add(new GameModel(entity));
        }
        return out;
    }

    @Override
    public GameModel createGame(GameModel gameModel) {
        Game gameEntity = new Game(gameModel);
        return new GameModel(gameRepository.save(gameEntity));
    }
    public List<HashMap<String,Object>> searchForFiveGames(String searchString){
        return gameRepository.findFirst5ByTitleContaining(searchString, Sort.unsorted()
        ).stream().map(this::reduceGameToIdAndTitle).collect(Collectors.toList());
    }

    private HashMap<String, Object> reduceGameToIdAndTitle(Game game){
        HashMap<String, Object> reducedGame = new HashMap<>();
        reducedGame.put("id", game.getId());
        reducedGame.put("title", game.getTitle());
        return reducedGame;
    }

    public List<Map<String, Object>> getTopRatedGames(Integer limit, Integer page) {
        PageRequest request = PageRequest.of(page-1, limit);
        List<Map<String, Object>> items = gameRepository.getTopRatedGames(request);
        return items;
    }

    @Override
    public List<GameModel> findAllGames() {
        return convertEntityListToModelList(gameRepository.findAll());
    }

    public GameModel findGameById(Long id) {
        Game gameEntity = gameRepository.findGameById(id);
        Float average = ratingRepository.getGameAverageRating(gameEntity.getId());
        return new GameModel(gameEntity, average);
    }

    @Override
    public Game findGameByTitle(String title) {
        return null;
    }*/
}
