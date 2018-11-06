package com.communityratesgames.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService implements RatingServiceInterface {

    @Autowired
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {this.ratingRepository = ratingRepository;}

    @Override
    public RatingModel createNewRating(RatingModel ratingModel) {
        RatingEntity rating = new RatingEntity(ratingModel);
        return new RatingModel(ratingRepository.save(rating));
    }

    @Override
    public List<RatingModel> findRatingsByGameId(Long gameId) {
        return convertEntityListToModelList(ratingRepository.findAllByGameId(gameId));
    }

    public RatingModel findByGameIdAndUserId(Long gameId, Long userId){
        RatingEntity ratingEntity = ratingRepository.findByGameIdAndUserId(gameId, userId);
        return new RatingModel(ratingEntity);
    }

/*
    public Long getRatingAverage(Long gameId){
        //TODO: Query for averages in one game
        return null;
    }
*/


    private List<RatingModel> convertEntityListToModelList(List<RatingEntity> entityList) {
        return entityList.stream().map(RatingModel::new).collect((Collectors.toList()));
    }
}

