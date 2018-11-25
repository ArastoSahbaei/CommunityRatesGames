package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.transactions.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

    public Company registerNewCompany(CompanyModel companyModel){ return companyDataAccess.registerNewCompany(companyModel); }
    public List<Game> showAllGames() {return gameDataAccess.showAllGames();}
    public List<Platform> showAllPlatforms() {return platformDataAccess.showAllPlatforms();}
    public List<User> showAllUsers() {return userDataAccess.showAllUsers();}
    public List<Rating> showAllRatings() {return  ratingDataAccess.showAllRatings();}
    public List<Company> showAllCompanies() {return companyDataAccess.showAllCompanies();}
}
