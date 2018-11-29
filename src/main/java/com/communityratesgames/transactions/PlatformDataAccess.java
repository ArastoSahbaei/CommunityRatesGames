package com.communityratesgames.transactions;

import com.communityratesgames.domain.Platform;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PlatformDataAccess {

    public List<Platform> showAllPlatforms();
    public Platform createPlatform(String name, int releaseYear, Long companyId);
}
