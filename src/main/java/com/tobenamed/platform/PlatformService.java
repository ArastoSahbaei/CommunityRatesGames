
package com.tobenamed.platform;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class PlatformService {
	@Autowired
	private PlatformRepository repo;

	public Platform getPlatformById(int id) {
		Optional<Platform> opt = repo.findById((Integer)id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	public void insertPlatform(Platform platform) {
		repo.save(platform);
	}

	public void deletePlatform(Platform platform) {
		repo.delete(platform);
	}
}
