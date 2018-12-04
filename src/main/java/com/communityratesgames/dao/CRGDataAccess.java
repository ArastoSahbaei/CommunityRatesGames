package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.user.AuthToken;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.transactions.*;
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

    public Company registerNewCompany(CompanyModel companyModel){ return companyDataAccess.registerNewCompany(companyModel); }
    public List<User> showAllUsers() {return userDataAccess.showAllUsers();}


    //Platform Access
    public List<Platform> showAllPlatforms() {return platformDataAccess.showAllPlatforms();}
    public Platform createPlatform(String name, int releaseYear, Long companyId) {return platformDataAccess.createPlatform(name, releaseYear, companyId);}

    //GameInterface Access
    public List<Game> showAllGames() {return gameDataAccess.showAllGames();}
    public List<Game> showVerifiedGames() {return gameDataAccess.showVerifiedGames();}
    public Game verifyGame(Long id) {return gameDataAccess.verifyGame(id);}
    public Game gameByTitle(String title) {
        return gameDataAccess.gameByTitle(title);
    }
    public Game gameById(Long id) {
        return gameDataAccess.gameById(id);
    }
    public String searchFiveGames(String query) {
        return gameDataAccess.searchFiveGames(query);
    }
    public Game createNewGame(Game newGame) {
        return gameDataAccess.createNewGame(newGame);
    }
    public List<Game> getTopRatedGames(Integer limit, Integer page) {
        return gameDataAccess.getTopRatedGames(limit, page);
    }

    //User
    public User register(User user) { return userDataAccess.register(user); }
    public AuthToken login(User user) {return userDataAccess.login(user);}
    public boolean logout(Long token) {return userDataAccess.logout(token);}

    public List<Rating> showAllRatings() {return  ratingDataAccess.showAllRatings();}
    public List<Company> showAllCompanies() {
        return companyDataAccess.showAllCompanies();
    }
}
