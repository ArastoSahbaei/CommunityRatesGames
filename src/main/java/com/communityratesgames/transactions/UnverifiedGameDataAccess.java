package com.communityratesgames.transactions;

import com.communityratesgames.model.GameModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UnverifiedGameDataAccess {
    public abstract void addUnverifiedGame(GameModel model);
    public abstract void deleteUnverifiedGame(Long id);
    public abstract void verifyGame(Long id);
    public abstract List<GameModel> getAllUnverifiedGames();
}
