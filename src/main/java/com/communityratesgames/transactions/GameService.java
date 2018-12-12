package com.communityratesgames.transactions;

import com.communityratesgames.domain.Game;
import com.communityratesgames.model.GameModel;
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
import java.util.stream.Collectors;

@Stateless
@Default
public class GameService implements GameDataAccess {

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public List<GameModel> showAllGames() {
        return convertListEntityToModel(
                em.createQuery("SELECT g FROM Game g", Game.class)
                .getResultList()
        );
    }

    @Override
    public GameModel gameByTitle(String title) {
        return new GameModel(
                em.createQuery("SELECT g FROM Game g WHERE g.title = :title",Game.class)
                .setParameter("title", title).getSingleResult()
        );
    }

    @Override
    public GameModel gameById(Long id) {
        return new GameModel(
                em.createQuery("SELECT g FROM Game g WHERE g.id = :id",Game.class)
                .setParameter("id", id)
                .getSingleResult()
        );
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
    public void createNewGame(Game newGame) {
    }

    @Override
    public List<GameModel> getTopRatedGames(Integer limit, Integer page) {
        return convertListEntityToModel(
                em.createQuery("SELECT g FROM Game g WHERE g.verified = TRUE ORDER BY g.averageRating DESC", Game.class)
                    .setFirstResult((page-1) * limit)
                    .setMaxResults(limit)
                    .getResultList()
        );
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

    private List<GameModel> convertListEntityToModel (List<Game> entityList) {
        return entityList.stream().map(GameModel::new).collect(Collectors.toList());
    }
}
