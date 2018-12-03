package com.communityratesgames.transactions;

import com.communityratesgames.domain.Rating;
import com.communityratesgames.model.RatingModel;

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
    public float getAverageOfGame(String gameTitle) {
        Double derp = (double)em.createQuery("SELECT AVG(r.rating) FROM Rating r WHERE r.game.title = :game")
                .setParameter("game",gameTitle).getSingleResult();
        return derp.floatValue();
    }

    @Override
    public List<Rating> findRatingsByGameId(String gameTitle) {
        return em.createQuery("SELECT r FROM Rating r WHERE r.game.title = :game")
                .setParameter("game",gameTitle).getResultList();
    }

    @Override
    public Rating findByGameIdAndUserId(String gameId, String userId) {
        System.out.println("Service " + gameId + " " + userId);
        try {
            return (Rating) em.createQuery("SELECT r FROM Rating r WHERE r.game.title = :gameId AND r.user.userName = :userId")
                    .setParameter("gameId",gameId)
                    .setParameter("userId",userId)
                    .getSingleResult();
        }catch (Exception e) {
            System.out.println("######### THE ERROL: " + e);
            return null;
        }


    }

    @Override
    public void addNewRating(RatingModel rating) {
        String gameId = rating.getGame();
        rating.setCreationDate(Timestamp.from(Instant.now()));
        try {
            if (findByGameIdAndUserId(gameId, rating.getUser()) == null) {
                em.persist(rating);
                System.out.println("###PERSISTED###");
            }else {
                em.createQuery("UPDATE Rating r SET r.rating = :rating, r.creationDate = :date WHERE r.user.userName = :user AND r.game.title = :game")
                        .setParameter("rating", rating.getRating())
                        .setParameter("date", rating.getCreationDate())
                        .setParameter("user", rating.getUser())
                        .setParameter("game", rating.getGame())
                        .executeUpdate();
                System.out.println("###UPDATED RATING###");
            }
        } catch (Exception e){
            System.out.println("Problem writing to database : " + e );
        }
        em.createQuery("UPDATE Game g SET g.averageRating = :average WHERE g.title = :game")
                .setParameter("average",getAverageOfGame(rating.getGame()))
                .setParameter("game", rating.getGame())
                .executeUpdate();
        System.out.println("###UPDATED AVERAGE###");
    }
}

