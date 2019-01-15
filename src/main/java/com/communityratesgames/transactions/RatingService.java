package com.communityratesgames.transactions;

import com.communityratesgames.domain.Game;
import com.communityratesgames.domain.Rating;
import com.communityratesgames.domain.User;
import com.communityratesgames.model.RatingModel;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Default
public class RatingService implements RatingDataAccess {

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public List<RatingModel> showAllRatings() {
        return convertListEntityToModel(
            em.createQuery("SELECT r FROM Rating r", Rating.class)
                .getResultList()
        );
    }

    @Override
    public List<RatingModel> findRatingsByGameId(String gameTitle) {
        return convertListEntityToModel(
            em.createQuery("SELECT r FROM Rating r WHERE r.game.title = :game",Rating.class)
                    .setParameter("game",gameTitle).getResultList()
        );
    }

    public List<RatingModel> findAllUserRatings(String username) {
        return convertListEntityToModel(
            em.createQuery("SELECT r FROM Rating r WHERE r.user.userName = :user", Rating.class)
                    .setParameter("user", username)
                    .getResultList()
        );
    }

    @Override
    public float getAverageOfGame(String gameTitle) {
        Object o = em.createQuery("SELECT AVG(r.rating) FROM Rating r WHERE r.game.title = :game",Double.class)
                .setParameter("game",gameTitle).getSingleResult();
        if (o == null) {
            return 0f;
        } else {
            // For some reason, createQuery ignores the second parameter for Double, so we need to forcibly check this.
            if (o instanceof Double) {
                return ((Double)o).floatValue();
            } else {
                return (int)((Integer)o);
            }
        }
    }

    @Override
    public RatingModel findByGameIdAndUserId(String title, String username) {
        try {
            return new RatingModel(
                em.createQuery("SELECT r FROM Rating r WHERE r.game.title = :title AND r.user.userName = :username", Rating.class)
                    .setParameter("title",title)
                    .setParameter("username",username)
                    .getSingleResult()
            );
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public RatingModel addNewRating(RatingModel rating) {
        try {
            rating.setCreationDate(Timestamp.from(Instant.now()));
            Rating newRating = ratingModelToEntity(rating);
            String title = rating.getGame();
            String user = rating.getUser();
            if (findByGameIdAndUserId(title, user) == null) {
                em.persist(newRating);
                System.out.printf("LOG: User %s added a new rating of game '%s' rated at %d",user,title,rating.getRating());
            }else {
                em.createQuery("UPDATE Rating r SET r.rating = :rating, r.creationDate = :date WHERE r.game = :game AND r.user = :user")
                        .setParameter("rating", rating.getRating())
                        .setParameter("date", rating.getCreationDate())
                        .setParameter("user", newRating.getUser())
                        .setParameter("game", newRating.getGame())
                        .executeUpdate();
                System.out.printf("LOG: User %s changed their rating of game '%s' to %d",user,title,rating.getRating());
            }
            em.createQuery("UPDATE Game g SET g.averageRating = :average WHERE g.title = :game")
                    .setParameter("average",getAverageOfGame(rating.getGame()))
                    .setParameter("game", rating.getGame())
                    .executeUpdate();
            return new RatingModel(newRating);
        } catch (NoResultException e) {
            return null;
        }
    }

    private Rating ratingModelToEntity(RatingModel model) {
        Rating entity = new Rating();
        entity.setGame(getGameFromTitle(model.getGame()));
        entity.setUser(getUserFromUsername(model.getUser()));

        entity.setRating(model.getRating());
        if(model.getCreationDate() != null) {
            entity.setCreationDate(Timestamp.from(Instant.now()));
        }else {
            entity.setCreationDate(model.getCreationDate());
        }
        return entity;
    }

    private User getUserFromUsername(String name) {
        return em.createQuery("SELECT u FROM User u WHERE u.userName = :username",User.class)
                .setParameter("username",name)
                .getSingleResult();

    }

    private Game getGameFromTitle(String title) {
        return em.createQuery("SELECT g FROM Game g WHERE g.title = :title",Game.class)
                .setParameter("title",title)
                .getSingleResult();
    }

    private List<RatingModel> convertListEntityToModel (List<Rating> entityList) {
        return entityList.stream().map(entity -> ((entity == null) ? null : new RatingModel(entity))).collect(Collectors.toList());
    }
}

