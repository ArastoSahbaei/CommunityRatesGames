package com.communityratesgames.transactions;

import com.communityratesgames.domain.Rating;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Stateless
@Default
public class RatingService implements RatingDataAccess {

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public List<Rating> showAllRatings() {
        Query q = em.createNativeQuery("SELECT * FROM rating_entity", Rating.class);
        List<Rating> rating = q.getResultList();
        return rating;
    }

    @Override
    public float getAverageOfGame(Long gameId) {
        Long derp = (Long)em.createQuery("SELECT AVG(r.rating) FROM Rating r WHERE r.game.id = :gameId")
                .setParameter("gameId",gameId).getSingleResult();
        return derp.floatValue();
    }

    @Override
    public List<Rating> findRatingsByGameId(Long gameId) {
        return em.createQuery("SELECT r FROM Rating r WHERE r.game.id = :gameId")
                .setParameter("gameId",gameId).getResultList();
    }

    @Override
    public Rating findByGameIdAndUserId(Long gameId, Long userId) {
        return (Rating) em.createQuery("SELECT r FROM Rating r WHERE r.game = :gameId AND r.user = :userId")
                .setParameter("gameId",gameId)
                .setParameter("userId",userId)
                .getSingleResult();
    }

    @Override
    public void addNewRating(Rating rating) {
        Long gameId = rating.getGame().getId();
        rating.setCreationDate(Timestamp.from(Instant.now()));
        try {
            if (findByGameIdAndUserId(gameId, rating.getUser().getId()) == null) {
                em.persist(rating);
                System.out.println("###PERSISTED###");
            }else {
                em.createQuery("UPDATE Rating r SET r.rating = :rating, r.creationDate = :date WHERE r.user = :userid AND r.game = :gameid")
                        .setParameter("rating", rating.getRating())
                        .setParameter("date", rating.getCreationDate())
                        .setParameter("userid", rating.getUser().getId())
                        .setParameter("gameid", rating.getGame().getId())
                        .executeUpdate();
                System.out.println("###UPDATED RATING###");
            }
        } catch (Exception e){
            System.out.println("Problem writing to database : " + e );
        }
        em.createQuery("UPDATE Game g SET g.averageRating = :average WHERE g.id = :gameId")
                .setParameter("average",getAverageOfGame(rating.getGame().getId()))
                .setParameter("gameId", rating.getGame().getId())
                .executeUpdate();
        System.out.println("###UPDATED AVERAGE###");
    }


}

