package com.communityratesgames.tests;

import com.communityratesgames.archive.Shrink;
import com.communityratesgames.model.RatingModel;
import com.communityratesgames.transactions.RatingDataAccess;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class RatingTest {

    @Inject
    private RatingDataAccess ratingDataAccess;

    @Deployment
    public static WebArchive createDeployment() {
        return Shrink.createDeployment();
    }

    @Test
    public void rateShouldBeNull() {
        RatingModel rm = ratingDataAccess.findByGameIdAndUserId("Sonic", "Bjorn");
        Assert.assertNull(rm);
    }

    @Test
    public void temp() {
       float rating = ratingDataAccess.getAverageOfGame("Halo");

       Assert.assertEquals(0,Float.compare(-1.0f, rating));
    }
}
