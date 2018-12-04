package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.model.RatingModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DataAccessLocal {

    //Company
    public Company registerNewCompany(CompanyModel companyModel);
    public List<Company> showAllCompanies();


    //Rating
    public List<Rating> showAllRatings();
    public List<Rating> findRatingsByGameId(String gameTitle);
    public float getAverageOfGame(String gameTitle);
    public Rating findByGameIdAndUserId(String gameTitle, String username);
    public void addNewRating(RatingModel rating);


    //Platform
    public List<Platform> showAllPlatforms();
    public Platform createPlatform(String name, int releaseYear, Long companyId);


    //User
    public List<User> showAllUsers();
    public User login(User user);
    public User register(User user);


    //Game
    public List<Game> showAllGames();
    public List<Game> showVerifiedGames();
    public Game verifyGame(Long id);
    public Game gameByTitle(String title);
    public Game gameById(Long id);
    public String searchFiveGames(String query);
    public Game createNewGame(Game newGame);
    public List<Game> getTopRatedGames(Integer limit, Integer page);
}
