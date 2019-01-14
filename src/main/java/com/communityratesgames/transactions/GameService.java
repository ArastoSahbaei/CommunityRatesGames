package com.communityratesgames.transactions;

import com.communityratesgames.domain.Company;
import com.communityratesgames.domain.Game;
import com.communityratesgames.domain.Platform;
import com.communityratesgames.model.GameModel;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.*;
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
        try {
            return new GameModel(
                    em.createQuery("SELECT g FROM Game g WHERE g.title = :title",Game.class)
                            .setParameter("title", title)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public GameModel gameById(Long id) {
        try {
            return new GameModel(
                    em.createQuery("SELECT g FROM Game g WHERE g.id = :id",Game.class)
                            .setParameter("id", id)
                            .getSingleResult()
            );
        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public String searchFiveGames(String query) {
        List<Game> results = em.createQuery("SELECT g FROM Game g WHERE g.title LIKE :title",Game.class)
                .setParameter("title", query+'%')
                .setMaxResults(5)
                .getResultList();
        return reduceGameToTitleAndId(results);
    }

    @Override
    public List<GameModel> getTopRatedGames(Integer limit, Integer page) {
        return convertListEntityToModel(
                em.createQuery("SELECT g FROM Game g ORDER BY g.averageRating DESC", Game.class)
                    .setFirstResult((page-1) * limit)
                    .setMaxResults(limit)
                    .getResultList()
        );
    }

    @Override
    public List<GameModel> getTop100Games() {
        return convertListEntityToModel(
                em.createQuery("SELECT g FROM Game g ORDER BY g.averageRating DESC", Game.class)
                        .setMaxResults(100)
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

    private Game gameModelToEntity(GameModel model) {
        return em.createQuery("SELECT g FROM Game g WHERE g.title = :title",Game.class)
                .setParameter("title", model.getTitle())
                .getSingleResult();
    }

    private List<GameModel> convertListEntityToModel (List<Game> entityList) {
        return entityList.stream().map(GameModel::new).collect(Collectors.toList());
    }

    //NOTE: Intended for test data
    public void addGame(GameModel model) {
        em.persist(createGameEntity(model));
    }

    private Game createGameEntity(GameModel model) {
        return new Game(
                model.getReleaseDate(),
                model.getTitle(),
                getCompanyEntity(model.getCompany()),
                getPlatformEntity(model.getPlatforms())
        );
    }
    private Company getCompanyEntity(String companyname) {
        return em.createQuery("SELECT c FROM Company c WHERE c.companyName = :name",Company.class)
                .setParameter("name", companyname)
                .getSingleResult();
    }
    private List<Platform> getPlatformEntity(List<String> platformList){
        return em.createQuery("SELECT p FROM Platform p WHERE p.name IN :list",Platform.class)
                .setParameter("list", platformList)
                .getResultList();
    }
}
