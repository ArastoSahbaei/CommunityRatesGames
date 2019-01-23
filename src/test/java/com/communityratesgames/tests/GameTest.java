package com.communityratesgames.tests;

import com.communityratesgames.archive.Shrink;
import com.communityratesgames.domain.Game;
import com.communityratesgames.model.GameModel;
import com.communityratesgames.transactions.GameDataAccess;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class GameTest {

    @Inject
    private GameDataAccess gameDataAccess;

    @Deployment
    public static WebArchive createDeployment() {
        return Shrink.createDeployment();
    }

    @Test @Ignore
    public void gamesInDatabase() {
       final List<GameModel> games = gameDataAccess.showAllGames();
       Assert.assertEquals(122, games.size());
    }

    @Test @Ignore
    public void getCertainGameById() {
        GameModel game = gameDataAccess.gameById(35L);
        Assert.assertEquals(game.getTitle(), "Sonic");
    }

    @Test @Ignore
    public void get100Games() {
        List<GameModel> games = gameDataAccess.getTop100Games();
        Assert.assertNotNull(games);
    }
}

