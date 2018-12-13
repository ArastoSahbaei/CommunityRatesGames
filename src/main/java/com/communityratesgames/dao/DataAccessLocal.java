package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.GameModel;
import com.communityratesgames.model.RatingModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DataAccessLocal {

    public Company registerNewCompany(Company company);
    public List<Company> showAllCompanies();


    //Rating
    public List<RatingModel> showAllRatings();
    public List<RatingModel> findRatingsByGameId(String gameTitle);
    public float getAverageOfGame(String gameTitle);
    public RatingModel findByGameIdAndUserId(String gameTitle, String username);
    public void addNewRating(RatingModel rating);
    public List<RatingModel> findAllUserRatings(String username);


    //Platform
    public List<Platform> showAllPlatforms();
    public Platform createPlatform(String name, int releaseYear, Long companyId);


    //User
    public List<User> showAllUsers();
    public User login(User user);
    public boolean logout(Long token);
    public User register(User user);


    //Game
    public List<GameModel> showAllGames();
    public GameModel gameByTitle(String title);
    public GameModel gameById(Long id);
    public String searchFiveGames(String query);
    public void createNewGame(GameModel newGame);
    public List<GameModel> getTopRatedGames(Integer limit, Integer page);

    //Unverified Game
    public void addUnverifiedGame(GameModel model);
    public void deleteUnverifiedGame(Long id);
    public void verifyGame(Long id);
    public List<GameModel> getAllUnverifiedGames();
}
