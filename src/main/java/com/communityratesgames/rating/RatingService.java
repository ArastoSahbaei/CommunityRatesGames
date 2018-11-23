package com.communityratesgames.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService implements RatingServiceInterface {

    @Autowired
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {this.ratingRepository = ratingRepository;}

    public RatingModel createNewRating(RatingModel ratingModel) {
        RatingEntity rating = new RatingEntity(ratingModel);
        rating.setCreationDate(Timestamp.from(Instant.now()));
        return new RatingModel(ratingRepository.save(rating));
    }

    public List<RatingModel> getAllRatings() {
        return convertEntityListToModelList(ratingRepository.findAll());
    }

    public float getAverageOfGame(Long gameId) {
        return ratingRepository.getGameAverageRating(gameId);
    }

    public List<RatingModel> findRatingsByGameId(Long gameId) {
        return convertEntityListToModelList(ratingRepository.findAllByGameId(gameId));
    }

    /*
    public RatingModel findByGameIdAndUserId(Long gameId, Long userId){
        RatingEntity ratingEntity = ratingRepository.findByGameIdAndUserId(gameId, userId);
        return new RatingModel(ratingEntity);
    }
*/

    private List<RatingModel> convertEntityListToModelList(List<RatingEntity> entityList) {
        return entityList.stream().map(RatingModel::new).collect((Collectors.toList()));
    }
}

