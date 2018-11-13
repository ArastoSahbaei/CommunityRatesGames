package com.communityratesgames.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.communityratesgames.game.GameEntity;
import com.communityratesgames.user.UserEntity;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    List<RatingEntity> findAllByGameId(long gameId);

    RatingEntity findByGameAndUser(GameEntity game, UserEntity user);

    @Query(value = "SELECT AVG(r.rating) FROM RatingEntity r WHERE r.game.id = :gameId")
    Float getGameAverageRating(@Param("gameId") long gameId);
}
