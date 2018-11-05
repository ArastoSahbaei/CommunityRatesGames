
package com.communityratesgames.platform;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class PlatformService implements PlatformServiceInterface {
	@Autowired
	private PlatformRepository repo;

	private List<PlatformModel> convertEntityListToModelList(List<PlatformEntity> list) {
		List<PlatformModel> out = new ArrayList<>();
		for (PlatformEntity entity : list) {
			out.add(new PlatformModel(entity));
		}
		return out;
	}

	public PlatformEntity getPlatformById(int id) {
		Optional<PlatformEntity> opt = repo.findById((Integer)id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	public List<PlatformModel> getPlatforms() {
		return convertEntityListToModelList(repo.findAll());
	}

	public void insertPlatform(PlatformEntity platform) {
		repo.save(platform);
	}

	public void deletePlatform(PlatformEntity platform) {
		repo.delete(platform);
	}
}
