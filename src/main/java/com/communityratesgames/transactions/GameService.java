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
import javax.persistence.StoredProcedureQuery;
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
        Game g = em.createQuery("SELECT g FROM Game g WHERE g.id = :id", Game.class)
                .setParameter("id", id)
                .getSingleResult();
        g.setVerified(true);
        em.persist(g);
        return g;
    }

    @Override
    public Game gameByTitle(String title) {
        return em.createQuery("SELECT g FROM Game g WHERE g.title = :title",Game.class)
                .setParameter("title", title).getSingleResult();
    }

    @Override
    public Game gameById(Long id) {
        return em.createQuery("SELECT g FROM Game g WHERE g.id = :id",Game.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public String searchFiveGames(String query) {
        StoredProcedureQuery searchForFiveGamesByTitle =
                em.createNamedStoredProcedureQuery("searchForFiveGamesByTitle");

        StoredProcedureQuery sp =
                searchForFiveGamesByTitle.setParameter("query",query);
        return reduceGameToTitleAndId(sp.getResultList());
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
}
