package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.CompanyModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DataAccessLocal {

    public Company registerNewCompany(CompanyModel companyModel);
    public List<Game> showAllGames();
    public List<Platform> showAllPlatforms();
    public List<User> showAllUsers();
    public List<Rating> showAllRatings();
    public List<Company> showAllCompanies();
    public User login(String email, String password);
    public Game gameByTitle(String title);
    public Game gameById(Long id);
    public String searchFiveGames(String query);
    public User register(User user);
}
