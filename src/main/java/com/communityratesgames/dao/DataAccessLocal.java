package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.user.AuthToken;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DataAccessLocal {

    public Company registerNewCompany(CompanyModel companyModel);
    public List<Rating> showAllRatings();
    public List<Company> showAllCompanies();


    //PlatformController
    public List<Platform> showAllPlatforms();
    public Platform createPlatform(String name, int releaseYear, Long companyId);


    //UserController
    public List<User> showAllUsers();
    public AuthToken login(User user);
    public boolean logout(Long token);
    public User register(User user);


    //GameController
    public List<Game> showAllGames();
    public List<Game> showVerifiedGames();
    public Game verifyGame(Long id);
    public Game gameByTitle(String title);
    public Game gameById(Long id);
    public String searchFiveGames(String query);
    public Game createNewGame(Game newGame);
    public List<Game> getTopRatedGames(Integer limit, Integer page);
}
