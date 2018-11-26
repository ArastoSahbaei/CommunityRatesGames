package com.communityratesgames.transactions;

import com.communityratesgames.domain.Platform;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PlatformDataAccess {

    public abstract List<Platform> showAllPlatforms();
}
