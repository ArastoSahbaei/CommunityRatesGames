package com.communityratesgames.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.communityratesgames.game.GameEntity;
import com.communityratesgames.user.UserEntity;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    List<RatingEntity> findAllByGame(GameEntity game);

    RatingEntity findByGameAndUser(GameEntity game, UserEntity user);

/*    Long getRatingAverage(Long gameId);*/
}
