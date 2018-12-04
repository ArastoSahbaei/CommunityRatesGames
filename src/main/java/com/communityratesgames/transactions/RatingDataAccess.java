package com.communityratesgames.transactions;

import com.communityratesgames.domain.Rating;
import com.communityratesgames.model.RatingModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RatingDataAccess {

    public abstract List<Rating> showAllRatings();
    public abstract float getAverageOfGame(String gameTitle);
    public abstract List<Rating> findRatingsByGameId(String gameTitle);
    public abstract Rating findByGameIdAndUserId(String gameTitle, String username);
    public abstract void addNewRating(RatingModel rating);
}
