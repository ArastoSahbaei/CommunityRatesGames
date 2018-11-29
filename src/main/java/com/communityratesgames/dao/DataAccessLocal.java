package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.CompanyModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DataAccessLocal {

    public Company registerNewCompany(CompanyModel companyModel);
    public List<Platform> showAllPlatforms();
    public List<Rating> showAllRatings();
    public List<Company> showAllCompanies();


    //UserController
    public List<User> showAllUsers();
    public User login(User user);
    public User register(User user);


    //GameController
    public List<Game> showAllGames();
    public Game gameByTitle(String title);
    public Game gameById(Long id);
    public String searchFiveGames(String query);
    public Game createNewGame(Game newGame);
    public List<Game> getTopRatedGames(Integer limit, Integer page);
}
