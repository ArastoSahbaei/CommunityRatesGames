package com.tobenamed.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    GameEntity findGameById(Long id);

    GameEntity findGameByTitle(String title);
}
