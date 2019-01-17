package com.communityratesgames.tests;

import com.communityratesgames.archive.Shrink;
import com.communityratesgames.domain.*;
import com.communityratesgames.transactions.*;
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
public class UserTest {

    @Inject
    private UserDataAccess userDataAccess;

    @Deployment
    public static WebArchive createDeployment() {
        return Shrink.createDeployment();
    }

    @Test
    public void getAllUsers() {
        final List<User> result = userDataAccess.showAllUsers();
        Assert.assertNotNull(result);
    }

    @Test @Ignore
    public void getOneUser() {
        final User user = userDataAccess.detailsAboutAUser("Bjorn");
        Assert.assertEquals("Bjorn", user.getUserName());
    }
    
}
