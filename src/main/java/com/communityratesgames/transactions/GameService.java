package com.communityratesgames.transactions;

import com.communityratesgames.domain.Game;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.*;
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
        return em.createQuery("SELECT g FROM Game g", Game.class)
                .getResultList();
    }

    @Override
    public List<Game> showVerifiedGames() {
        return em.createQuery("SELECT g FROM Game g WHERE g.verified = TRUE", Game.class)
                .getResultList();
    }

    @Override
    public Game verifyGame(Long id) {
        try {
            Game g = em.createQuery("SELECT g FROM Game g WHERE g.id = :id", Game.class)
                    .setParameter("id", id)
                    .getSingleResult();
            g.setVerified(true);
            em.persist(g);
            return g;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Game gameByTitle(String title) {
        try {
            return em.createQuery("SELECT g FROM Game g WHERE g.title = :title",Game.class)
                    .setParameter("title", title).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Game gameById(Long id) {
        try {
            return em.createQuery("SELECT g FROM Game g WHERE g.id = :id",Game.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public String searchFiveGames(String query) {

            List<Game> results = em.createQuery("SELECT g FROM Game g WHERE g.title LIKE :title AND g.verified = TRUE",Game.class)
                    .setParameter("title", query+'%')
                    .setMaxResults(5)
                    .getResultList();
            return reduceGameToTitleAndId(results);
    }

    @Override
    public Game createNewGame(Game newGame) {
        em.persist(newGame);
        return newGame;
    }

    @Override
    public List<Game> getTopRatedGames(Integer limit, Integer page) {
            return em.createQuery("SELECT g FROM Game g WHERE g.verified = TRUE ORDER BY g.averageRating DESC", Game.class)
                    .setFirstResult((page-1) * limit)
                    .setMaxResults(limit)
                    .getResultList();
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
