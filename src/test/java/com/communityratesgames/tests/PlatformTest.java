package com.communityratesgames.tests;

import com.communityratesgames.archive.Shrink;
import com.communityratesgames.domain.Platform;
import com.communityratesgames.transactions.PlatformDataAccess;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RunWith(Arquillian.class)
public class PlatformTest {

    @Inject
    private PlatformDataAccess platformDataAccess;

    @Deployment
    public static WebArchive createDeployment() {
        return Shrink.createDeployment();
    }

    private String[] platforms() {
        String platforms[] = new String[]{
                "XBox", "Playstation", "Nintendo", "PC"
        };
        return platforms;
    }

    @Test @Ignore
    public void shouldBeFourPlatforms() {
        List<Platform> platforms = platformDataAccess.showAllPlatforms();

        Assert.assertEquals(4, platforms.size());
    }

    @Test @Ignore
    public void checkFourManufacturers() {
        final List<Platform> platforms = platformDataAccess.showAllPlatforms();
        final String[] testPlatforms = platforms();

        String platformsFromBackend[] = new String[4];

        for (int i = 0; i < platforms.size(); i++ ) {
            platformsFromBackend[i] = platforms.get(i).getName();
        }

        Arrays.sort(platformsFromBackend);
        Arrays.sort(testPlatforms);

        Assert.assertArrayEquals(testPlatforms, platformsFromBackend);
    }
}
