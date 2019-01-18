package com.communityratesgames.dao;

import com.communityratesgames.domain.*;
import com.communityratesgames.model.GameModel;
import com.communityratesgames.model.RatingModel;
import com.communityratesgames.util.FileLimitReachedException;
import com.communityratesgames.util.InvalidFileFormatException;
import com.communityratesgames.util.JsonError;

import javax.ejb.Local;
import java.io.IOException;
import java.util.List;

@Local
public interface DataAccessLocal {

    public Company registerNewCompany(Company company);
    public List<Company> showAllCompanies();
    public Company findCompanyByCompanyName(String companyName);


    //Rating
    public List<RatingModel> showAllRatings();
    public List<RatingModel> findRatingsByGameId(String gameTitle);
    public float getAverageOfGame(String gameTitle);
    public RatingModel findByGameIdAndUserId(String gameTitle, String username);
    public RatingModel addNewRating(RatingModel rating);
    public List<RatingModel> findAllUserRatings(String username);


    //Platform
    public List<Platform> showAllPlatforms();
    public Platform createPlatform(String name, int releaseYear, Long companyId);

    //User
    public List<User> showAllUsers();
    public Long login(User user);
    public User getUserToken(Long token);
    public boolean logout(Long token);
    public User register(User user) throws JsonError;
    public User detailsAboutAUser(String user);
    public Boolean deleteAUser(User user);
    public Integer updateAUser(User user);
    public User setUserAvatar(User user, byte[] image) throws IOException, FileLimitReachedException, InvalidFileFormatException;

    //Game
    public List<GameModel> showAllGames();
    public GameModel gameByTitle(String title);
    public GameModel gameById(Long id);
    public String searchFiveGames(String query);
    public List<GameModel> getTopRatedGames(Integer limit, Integer page);
    public List<GameModel> getTop100Games();
    public void addGame(GameModel model);

    //Unverified Game
    public void addUnverifiedGame(GameModel model);
    public void deleteUnverifiedGame(Long id);
    public void verifyGame(Long id);
    public List<GameModel> getAllUnverifiedGames();
}
