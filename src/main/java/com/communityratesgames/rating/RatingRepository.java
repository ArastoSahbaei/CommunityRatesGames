package com.communityratesgames.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    List<RatingEntity> findAllByGameId(Long gameId);

    RatingEntity findByGameIdAndUserId(Long gameId, Long userId);

    Long getRatingAverage(Long gameId);
    /*
        Find
        getRatingAverage
    */
}