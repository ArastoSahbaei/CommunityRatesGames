package com.communityratesgames.transactions;

import com.communityratesgames.domain.Game;
import com.communityratesgames.domain.Rating;
import com.communityratesgames.domain.User;
import com.communityratesgames.model.RatingModel;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
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
        return em.createQuery("SELECT r FROM Rating r", Rating.class)
                .getResultList();
    }

    @Override
    public float getAverageOfGame(String gameTitle) {
        return em.createQuery("SELECT AVG(r.rating) FROM Rating r WHERE r.game.title = :game",Double.class)
                .setParameter("game",gameTitle).getSingleResult().floatValue();
    }

    @Override
    public List<Rating> findRatingsByGameId(String gameTitle) {
        return em.createQuery("SELECT r FROM Rating r WHERE r.game.title = :game",Rating.class)
                .setParameter("game",gameTitle).getResultList();
    }

    @Override
    public Rating findByGameIdAndUserId(String title, String username) {
        try {
            return em.createQuery("SELECT r FROM Rating r WHERE r.game.title = :title AND r.user.userName = :username", Rating.class)
                    .setParameter("title",title)
                    .setParameter("username",username)
                    .getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addNewRating(RatingModel rating) {
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
        } catch (Exception e){
            System.out.println("Problem writing to database : " + e );
        }
    }

    private Rating ratingModelToEntity(RatingModel model) throws Exception{
        Rating entity = new Rating();
        try {
            entity.setGame(getGameFromTitle(model.getGame()));
            entity.setUser(getUserFromUsername(model.getUser()));
        }catch (Exception e) {
            throw new Exception("Failed to set entities: " + e.getMessage(), e);
        }
        entity.setRating(model.getRating());
        if(model.getCreationDate() != null) {
            entity.setCreationDate(Timestamp.from(Instant.now()));
        }else {
            entity.setCreationDate(model.getCreationDate());
        }
        return entity;
    }

    private User getUserFromUsername(String name) throws Exception {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.userName = :username",User.class)
                    .setParameter("username",name)
                    .getSingleResult();
        }catch (Exception e) {
            throw new SQLException("Failed to get user : " + e.getMessage(), e);
        }

    }

    private Game getGameFromTitle(String title) throws Exception {
        try {
            return em.createQuery("SELECT g FROM Game g WHERE g.title = :title",Game.class)
                    .setParameter("title",title)
                    .getSingleResult();
        }catch (Exception e) {
            throw new SQLException("Failed to get game : " + e.getMessage(), e);
        }
    }
}

