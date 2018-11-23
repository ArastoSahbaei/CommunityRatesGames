package com.communityratesgames.game;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    GameEntity findGameById(Long id);

    GameEntity findGameByTitle(String title);

    List<GameEntity> findFirst5ByTitleContaining(String searchString, Sort sort);

    @Query(value="SELECT g.id AS id, g.title AS title, AVG(r.rating) AS average " +
        "FROM GameEntity g RIGHT JOIN RatingEntity r ON r.game.id = g.id " +
        "GROUP BY g.id ORDER BY average DESC")
    List<Map<String,Object>> getTopRatedGames(Pageable pageable);
}
