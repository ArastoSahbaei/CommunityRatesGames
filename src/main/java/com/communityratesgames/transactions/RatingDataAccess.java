package com.communityratesgames.transactions;

import com.communityratesgames.domain.Rating;
import com.communityratesgames.model.RatingModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RatingDataAccess {

    public abstract List<RatingModel> showAllRatings();
    public abstract float getAverageOfGame(String gameTitle);
    public abstract float getCountOfRatings(String gameTitle);
    public abstract List<RatingModel> findRatingsByGameId(String gameTitle);
    public abstract RatingModel findByGameIdAndUserId(String gameTitle, String username);
    public abstract RatingModel addNewRating(RatingModel rating);
    public abstract List<RatingModel> findAllUserRatings(String username);
}
