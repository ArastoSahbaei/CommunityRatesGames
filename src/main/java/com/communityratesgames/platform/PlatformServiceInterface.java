
package com.communityratesgames.platform;

public interface PlatformServiceInterface {
	public Platform getPlatformById(int id);
	public void insertPlatform(Platform platform);
	public void deletePlatform(Platform platform);
}
