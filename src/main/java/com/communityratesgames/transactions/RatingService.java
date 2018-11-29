package com.communityratesgames.transactions;

import com.communityratesgames.domain.Rating;

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
}

