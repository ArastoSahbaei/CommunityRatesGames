package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.GameModel;
import com.communityratesgames.model.RatingModel;
import com.communityratesgames.transactions.*;
import com.communityratesgames.util.JsonError;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CRGDataAccess implements DataAccessLocal, DataAccessRemote {

    private final static Logger logger = Logger.getLogger(com.communityratesgames.dao.CRGDataAccess.class);

    //Injects to all interfaces for each entity

    @Inject
    private CompanyDataAccess companyDataAccess;

    @Inject
    private GameDataAccess gameDataAccess;

    @Inject
    private PlatformDataAccess platformDataAccess;

    @Inject
    private RatingDataAccess ratingDataAccess;

    @Inject
    private UserDataAccess userDataAccess;

    @Inject
    private UnverifiedGameDataAccess unverifiedGameDataAccess;


    //PlatformInterface Access
    public List<Platform> showAllPlatforms() {return platformDataAccess.showAllPlatforms();}
    public Platform createPlatform(String name, int releaseYear, Long companyId) {return platformDataAccess.createPlatform(name, releaseYear, companyId);}

    //GameInterface Access
    public List<GameModel> showAllGames() {
        return gameDataAccess.showAllGames();
    }
    public GameModel gameByTitle(String title) {
        return gameDataAccess.gameByTitle(title);
    }
    public GameModel gameById(Long id) {
        return gameDataAccess.gameById(id);
    }
    public String searchFiveGames(String query) {
        return gameDataAccess.searchFiveGames(query);
    }
    public List<GameModel> getTopRatedGames(Integer limit, Integer page) {
        return gameDataAccess.getTopRatedGames(limit, page);
    }

    //Unverified Games
    public void addUnverifiedGame(GameModel model) {
        unverifiedGameDataAccess.addUnverifiedGame(model);
    }
    public void deleteUnverifiedGame(Long id) {
        unverifiedGameDataAccess.deleteUnverifiedGame(id);
    }
    public void verifyGame(Long id) {
        unverifiedGameDataAccess.verifyGame(id);
    }
    public List<GameModel> getAllUnverifiedGames() {
        return unverifiedGameDataAccess.getAllUnverifiedGames();
    }

    //User
    public List<User> showAllUsers() {return userDataAccess.showAllUsers();}
    public User register(User user) throws JsonError { return userDataAccess.register(user); }
    public User login(User user) {return userDataAccess.login(user);}
    public boolean logout(Long token) {return userDataAccess.logout(token);}

    //Rating Access
    public List<RatingModel> showAllRatings() {return ratingDataAccess.showAllRatings();}
    public List<RatingModel> findRatingsByGameId(String gameTitle) {
        return ratingDataAccess.findRatingsByGameId(gameTitle);
    }
    public float getAverageOfGame(String gameTitle) {
        return ratingDataAccess.getAverageOfGame(gameTitle);
    }
    public RatingModel findByGameIdAndUserId(String gameTitle, String username) {
        return ratingDataAccess.findByGameIdAndUserId( gameTitle, username);
    }
    public RatingModel addNewRating(RatingModel rating) {
        return ratingDataAccess.addNewRating(rating);
    }
    public List<RatingModel> findAllUserRatings(String username) {
        return ratingDataAccess.findAllUserRatings(username);
    }

    //Company Access
    public List<Company> showAllCompanies() {
        return companyDataAccess.showAllCompanies();
    }

    public Company registerNewCompany(Company companyModel){
        return companyDataAccess.registerNewCompany(companyModel);
    }

    public Company findCompanyByCompanyName(String companyName){
        return companyDataAccess.findCompanyByCompanyName(companyName);
    }
}


