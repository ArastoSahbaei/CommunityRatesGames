package com.communityratesgames.game;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    GameEntity findGameById(Long id);

    GameEntity findGameByTitle(String title);

    List<GameEntity> findFirst5ByTitleContaining(String searchString, Sort sort);

}
