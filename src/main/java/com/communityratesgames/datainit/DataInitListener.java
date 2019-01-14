package com.communityratesgames.datainit;

import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.domain.Company;
import com.communityratesgames.domain.User;
import com.communityratesgames.model.GameModel;
import com.communityratesgames.model.RatingModel;
import com.communityratesgames.model.UserModel;
import com.communityratesgames.util.JsonError;
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
            System.out.println("### Starting initialization of test data ###");
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("testdata.json");
            DataCollection dataHolder = mapper.readValue(is, DataCollection.class);
            createCompanies(dataHolder.companies);
            createPlatforms(dataHolder.platforms);
            createGames(dataHolder.games);
            createUser(dataHolder.users);
            createRatings(dataHolder.ratings);
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

    private void createUser(List<UserModel> users){
            users.forEach(u -> {
                try {
                    System.out.println(u.toString());
                    User user = new User(u);
                    user.encryptPassword(u.getPassword());
                    dal.register(user);
                } catch (JsonError jsonError) {
                    jsonError.printStackTrace();
                }
            });
    }

    private void createRatings(List<RatingModel> ratings) {
        ratings.forEach(r -> dal.addNewRating(r));
    }

    static class DataCollection {
        List<Company> companies;
        List<Map<String,Object>> platforms;
        List<GameModel> games;
        List<RatingModel> ratings;
        List<UserModel> users;

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

        public List<RatingModel> getRatings() {
            return ratings;
        }

        public void setRatings(List<RatingModel> ratings) {
            this.ratings = ratings;
        }

        public List<UserModel> getUsers() {
            return users;
        }

        public void setUsers(List<UserModel> users) {
            this.users = users;
        }
    }
}
