package com.communityratesgames.datainit;

import com.communityratesgames.domain.Company;
import com.communityratesgames.domain.Game;
import com.communityratesgames.domain.Platform;
import com.communityratesgames.model.GameModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class DataInitListener implements javax.servlet.ServletContextListener {

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        derp();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
    public void derp() {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("###################");
        System.out.println("###################");
        System.out.println("###################");
        System.out.println("###################");

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("testdata.json");
            DataCollection dataHolder = mapper.readValue(is, DataCollection.class);
            createCompanies(dataHolder.companies);
            createPlatforms(dataHolder.platforms);
            createGames(dataHolder.games);
        }catch(IOException e) {
            System.out.println("ERROL" + e);
        }
    }
    private void createCompanies(List<Company> companies){
        companies.forEach(c -> em.persist(c));
    }

    private void createPlatforms(List<Map<String,Object>> platforms) {
        platforms.forEach(p -> em.persist(new Platform(
                (String)p.get("name"),
                (int)p.get("releaseYear"),
                getCompanyEntity((String) p.get("company")),
                null
        )));
    }

    private void createGames(List<GameModel> games){
            games.forEach(g -> em.persist(createGameEntity(g)));
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

    static class DataCollection {
        List<Company> companies;
        List<Map<String,Object>> platforms;
        List<GameModel> games;

        public DataCollection() {
        }

        public List<Company> getCompanies() {
            return companies;
        }

        public void setCompanies(List<Company> companies) {
            this.companies = companies;
        }

        public List<Map<String, Object>> getPlatforms() {
            return platforms;
        }

        public void setPlatforms(List<Map<String, Object>> platforms) {
            this.platforms = platforms;
        }

        public List<GameModel> getGames() {
            return games;
        }

        public void setGames(List<GameModel> games) {
            this.games = games;
        }
    }
}
