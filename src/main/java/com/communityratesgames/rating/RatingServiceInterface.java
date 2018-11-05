package com.communityratesgames.rating;

import java.util.List;

public interface RatingServiceInterface {

    RatingModel createNewRating(RatingModel ratingModel);

    List<RatingModel> findRatingsByGameId(Long gameId);

    RatingModel findByGameIdAndUserId(Long gameId, Long userId);

    Long getRatingAverage(Long gameId);

}
