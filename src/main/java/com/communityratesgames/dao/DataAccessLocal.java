package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.model.RatingModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DataAccessLocal {

    public Company registerNewCompany(CompanyModel companyModel);
    public List<Platform> showAllPlatforms();
    public List<Company> showAllCompanies();


    //RatingController
    public List<Rating> showAllRatings();
    public List<Rating> findRatingsByGameId(Long gameId);
    public float getAverageOfGame(Long gameId);
    public Rating findByGameIdAndUserId(Long gameId, Long userId);
    public void addNewRating(Rating rating);


    //UserController
    public List<User> showAllUsers();
    public User login(String email, String password);
    public User register(User user);


    //GameController
    public List<Game> showAllGames();
    public Game gameByTitle(String title);
    public Game gameById(Long id);
    public String searchFiveGames(String query);
    public Game createNewGame(Game newGame);
    public List<Game> getTopRatedGames(Integer limit, Integer page);
}
