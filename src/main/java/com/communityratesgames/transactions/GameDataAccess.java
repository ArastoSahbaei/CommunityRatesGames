package com.communityratesgames.transactions;

import com.communityratesgames.domain.Game;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GameDataAccess {
    public abstract List<Game> showAllGames();
    public abstract Game gameByTitle(String title);
    public abstract Game gameById(Long id);
    public abstract String searchFiveGames(String query);
    public abstract Game createNewGame(Game newGame);
    public abstract List<Game> getTopRatedGames(Integer limit, Integer page);
}
