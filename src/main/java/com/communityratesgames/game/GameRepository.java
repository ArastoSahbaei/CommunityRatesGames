package com.communityratesgames.game;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    GameEntity findGameById(Long id);

    GameEntity findGameByTitle(String title);

    List<GameEntity> findFirst5ByTitle(String searchString, Sort sort);

    /*@Query(value = "SELECT g FROM GameEntity g WHERE g.title LIKE %:searchString% ")
    List<String> searchForGames(@Param("searchstring") String searchString){}*/
}
