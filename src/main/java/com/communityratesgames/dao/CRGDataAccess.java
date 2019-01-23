package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.AdminContactModel;
import com.communityratesgames.model.GameModel;
import com.communityratesgames.model.RatingModel;
import com.communityratesgames.transactions.*;
import com.communityratesgames.util.FileLimitReachedException;
import com.communityratesgames.util.InvalidFileFormatException;
import com.communityratesgames.util.JsonError;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;

@Stateless
public class CRGDataAccess implements DataAccessLocal, DataAccessRemote {

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

    @Inject
    private AdminContactDataAccess adminContactDataAccess;


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
    public List<GameModel> getTop100Games() {
        return gameDataAccess.getTop100Games();
    }
    public void addGame(GameModel model) {gameDataAccess.addGame(model);

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
    public Long login(User user) {return userDataAccess.login(user);}
    public User getUserToken(Long token) {return userDataAccess.getUserToken(token);}
    public boolean logout(Long token) {return userDataAccess.logout(token);}
    public User detailsAboutAUser(String user) {return userDataAccess.detailsAboutAUser(user);}
    public Boolean deleteAUser(User user) {return userDataAccess.deleteAUser(user);}
    public Integer updateAUser(User user) {return userDataAccess.updateAUser(user);}
    public User setUserAvatar(User user, InputStream image) throws IOException, FileLimitReachedException, InvalidFileFormatException {return userDataAccess.setUserAvatar(user, image);}
    public File getUserAvatar(User user) throws IOException {return userDataAccess.getUserAvatar(user);}
    public void deleteUserAvatar(User user) throws IOException {userDataAccess.deleteUserAvatar(user);}

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
    public float getCountOfRatings(String gameTitle) {
        return ratingDataAccess.getCountOfRatings(gameTitle);
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

    //AdminContact
    public void newMessage(AdminContactModel model){adminContactDataAccess.newMessage(model);}
    public AdminContact adminGetMessage(Long id){return adminContactDataAccess.adminGetMessage(id);}
    public List<AdminContact> adminGetAllMessages(){return adminContactDataAccess.adminGetAllMessages();}
    public List<AdminContactModel> userMessages(String email){return adminContactDataAccess.userMessages(email);}
    public void updateEntry(AdminContact contact){adminContactDataAccess.updateEntry(contact);}
}


