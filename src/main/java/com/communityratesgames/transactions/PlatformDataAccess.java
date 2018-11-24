package com.communityratesgames.transactions;

import com.communityratesgames.domain.PlatformEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PlatformDataAccess {

    public abstract List<PlatformEntity> showAllPlatforms();
}
