package com.communityratesgames.transactions;

import com.communityratesgames.domain.GameEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GameDataAccess {
    public abstract List<GameEntity> showAllGames();
}
