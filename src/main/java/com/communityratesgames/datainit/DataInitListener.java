package com.communityratesgames.datainit;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Company;
import com.communityratesgames.model.GameModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import java.io.InputStream;
import java.util.*;

public class DataInitListener implements javax.servlet.ServletContextListener {

    @Inject
    private DataAccessLocal dal;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TestDataInitializer();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
    public void TestDataInitializer() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("testdata.json");
            DataCollection dataHolder = mapper.readValue(is, DataCollection.class);
            createCompanies(dataHolder.companies);
            createPlatforms(dataHolder.platforms);
            createGames(dataHolder.games);
        }catch(Exception e) {
        }
    }
    private void createCompanies(List<Company> companies){
        companies.forEach(c -> dal.registerNewCompany(c));
    }

    private void createPlatforms(List<Map<String,Object>> platforms) {
        platforms.forEach(p -> {
            long l = (int)p.get("company");
            dal.createPlatform(
                    (String)p.get("name"),
                    (int)p.get("releaseYear"),
                    l
            );}
        );
    }

    private void createGames(List<GameModel> games){
            games.forEach(g -> dal.addGame(g));
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
