package com.communityratesgames.transactions;

import com.communityratesgames.domain.Game;
import com.communityratesgames.model.GameModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GameDataAccess {
    public abstract List<GameModel> showAllGames();
    public abstract GameModel gameByTitle(String title);
    public abstract GameModel gameById(Long id);
    public abstract String searchFiveGames(String query);
    public abstract void createNewGame(Game newGame);
    public abstract List<GameModel> getTopRatedGames(Integer limit, Integer page);
}
