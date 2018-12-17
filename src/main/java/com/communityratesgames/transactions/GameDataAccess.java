package com.communityratesgames.transactions;

import com.communityratesgames.model.GameModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GameDataAccess {
    public abstract List<GameModel> showAllGames();
    public abstract GameModel gameByTitle(String title);
    public abstract GameModel gameById(Long id);
    public abstract String searchFiveGames(String query);
    public abstract List<GameModel> getTopRatedGames(Integer limit, Integer page);
    public abstract List<GameModel> getTop100Games();
}
