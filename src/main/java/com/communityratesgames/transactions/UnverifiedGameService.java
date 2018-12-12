package com.communityratesgames.transactions;

import com.communityratesgames.domain.UnverifiedGame;
import com.communityratesgames.model.UnverifiedGameModel;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Default
public class UnverifiedGameService implements UnverifiedGameDataAccess{

    @Override
    public void addUnverifiedGame(UnverifiedGameModel model) {

    }

    @Override
    public void deleteUnverifiedGame(Long id) {

    }

    @Override
    public void verifyGame(Long id) {

    }

    @Override
    public List<UnverifiedGameModel> getAllUnverifiedGames() {
        return null;
    }

    private List<UnverifiedGameModel> convertListEntityToModel (List<UnverifiedGame> entityList) {
        return entityList.stream().map(UnverifiedGameModel::new).collect(Collectors.toList());
    }
}
