package com.communityratesgames.archive;

import com.communityratesgames.dao.CRGDataAccess;
import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.dao.DataAccessRemote;
import com.communityratesgames.domain.*;
import com.communityratesgames.jms.JMSSender;
import com.communityratesgames.model.*;
import com.communityratesgames.rest.UserController;
import com.communityratesgames.transactions.*;
import com.communityratesgames.util.JsonError;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class Shrink {

    public static WebArchive createDeployment()
    {
        return
                ShrinkWrap.create(WebArchive.class, "test.war")
                        .addClasses(UserController.class)
                        .addClasses(JsonError.class)
                        .addClasses(UserModel.class, CompanyModel.class, GameModel.class, PlatformModel.class, RatingModel.class)
                        .addClasses(User.class, Company.class, Platform.class, Rating.class, UnverifiedGame.class, Game.class)
                        .addClasses(JMSSender.class)
                        .addClasses(DataAccessLocal.class, CRGDataAccess.class, DataAccessRemote.class)
                        .addClasses(CompanyService.class, GameService.class, PlatformService.class, RatingService.class, UnverifiedGameService.class, UserService.class)
                        .addClasses(CompanyDataAccess.class, GameDataAccess.class, PlatformDataAccess.class, RatingDataAccess.class, UnverifiedGameDataAccess.class, UserDataAccess.class)
                        .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
