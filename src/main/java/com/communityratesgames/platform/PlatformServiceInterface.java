
package com.communityratesgames.platform;

public interface PlatformServiceInterface {
	public PlatformEntity getPlatformById(int id);
	public void insertPlatform(PlatformModel platform);
	public void deletePlatform(PlatformEntity platform);
}
