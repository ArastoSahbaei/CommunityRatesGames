package com.communityratesgames.transactions;

import com.communityratesgames.domain.Game;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Stateless
@Default
public class GameService implements GameDataAccess {

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public List<Game> showAllGames() {
        Query q = em.createQuery("SELECT g FROM Game g", Game.class);
        return (List<Game>) q.getResultList();
    }

    @Override
    public List<Game> showVerifiedGames() {
        Query q = em.createQuery("SELECT g FROM Game g WHERE g.verified = TRUE", Game.class);
        return (List<Game>) q.getResultList();
    }

    @Override
    public Game verifyGame(Long id) {
        Game g = (Game)em.createQuery("SELECT g FROM Game g WHERE g.id = :id", Game.class)
                .setParameter("id", id)
                .getSingleResult();
        g.setVerified(true);
        em.persist(g);
        return g;
    }

    @Override
    public Game gameByTitle(String title) {
        Query q = em.createQuery("SELECT g FROM Game g WHERE g.title = :title",Game.class);
        q.setParameter("title", title);
        return (Game)q.getSingleResult();
    }

    @Override
    public Game gameById(Long id) {
        Query q = em.createQuery("SELECT g FROM Game g WHERE g.id = :id",Game.class)
                .setParameter("id", id);
        return (Game)q.getSingleResult();
    }

    @Override
    public String searchFiveGames(String query) {
        Query q = em.createNativeQuery("SELECT * FROM game_entity WHERE title LIKE ? AND verified = TRUE LIMIT 5",Game.class)
                .setParameter(1, query+'%')
                .setMaxResults(5);
        return reduceGameToTitleAndId(q.getResultList());
    }

    @Override
    public Game createNewGame(Game newGame) {
        em.persist(newGame);
        return newGame;
    }

    @Override
    public List<Game> getTopRatedGames(Integer limit, Integer page) {
        Query q = em.createQuery("SELECT g FROM Game g WHERE g.verified = TRUE", Game.class)
                .setFirstResult(page * limit)
                .setMaxResults(limit);
        return (List<Game>)q.getResultList();
    }

    private String reduceGameToTitleAndId(List<Game> gameList) {
        JsonFactory factory = new JsonFactory();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            JsonGenerator jgen = factory.createGenerator(outputStream);
            jgen.writeStartArray();
            for (Game game:gameList
            ) {
                jgen.writeStartObject();
                jgen.writeObjectField("id",game.getId());
                jgen.writeObjectField("title", game.getTitle());
                jgen.writeEndObject();
            }
            jgen.writeEndArray();
            jgen.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }

/*
    @Override
    public GameModel createGame(GameModel gameModel) {
        GameInterface gameEntity = new GameInterface(gameModel);
        return new GameModel(gameRepository.save(gameEntity));
    }

    public List<Map<String, Object>> getTopRatedGames(Integer limit, Integer page) {
        PageRequest request = PageRequest.of(page-1, limit);
        List<Map<String, Object>> items = gameRepository.getTopRatedGames(request);
        return items;
    }
*/
}
