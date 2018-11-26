package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.CompanyModel;
import com.communityratesgames.transactions.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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

    public Company registerNewCompany(CompanyModel companyModel){
        System.out.println("HEJ HEJ HEJ");
        logger.debug("My Debug Log");
        logger.info("My Info Log");
        logger.warn("My Warn Log");
        logger.error("My error log");
        logger.fatal("My fatal log");
        return companyDataAccess.registerNewCompany(companyModel);
    }
    public List<Game> showAllGames() {return gameDataAccess.showAllGames();}
    public List<Platform> showAllPlatforms() {return platformDataAccess.showAllPlatforms();}
    public List<User> showAllUsers() {
        System.out.println("HEJ HEJ HEJ");
        logger.debug("My Debug Log");
        logger.info("My Info Log");
        logger.warn("My Warn Log");
        logger.error("My error log");
        logger.fatal("My fatal log");
        return userDataAccess.showAllUsers();
    }
    public User login(String user) {return userDataAccess.login(user);}
    public List<Rating> showAllRatings() {return  ratingDataAccess.showAllRatings();}
    public List<Company> showAllCompanies() {
        System.out.println("HEJ HEJ HEJ");
        logger.debug("My Debug Log");
        logger.info("My Info Log");
        logger.warn("My Warn Log");
        logger.error("My error log");
        logger.fatal("My fatal log");
        return companyDataAccess.showAllCompanies();
    }
}
