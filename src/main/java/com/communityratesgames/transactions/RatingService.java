package com.communityratesgames.transactions;

import com.communityratesgames.domain.RatingEntity;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Default
public class RatingService implements RatingDataAccess {

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public List<RatingEntity> showAllRatings() {
        Query q = em.createNativeQuery("SELECT * FROM rating_entity", RatingEntity.class);
        List<RatingEntity> rating = q.getResultList();
        return rating;
    }
/*
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


    private List<RatingModel> convertEntityListToModelList(List<RatingEntity> entityList) {
        return entityList.stream().map(RatingModel::new).collect((Collectors.toList()));
    }*/
}

