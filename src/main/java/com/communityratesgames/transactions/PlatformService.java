
package com.communityratesgames.transactions;

import com.communityratesgames.domain.Platform;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Default
public class PlatformService implements PlatformDataAccess {

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public List<Platform> showAllPlatforms() {
        Query q = em.createNativeQuery("SELECT * FROM platform_entity", Platform.class);
        List<Platform> platforms = q.getResultList();
        return platforms;
    }
/*
    @Autowired
    private PlatformRepository repo;

    private List<PlatformModel> convertEntityListToModelList(List<Platform> list) {
        List<PlatformModel> out = new ArrayList<>();
        for (Platform entity : list) {
            out.add(new PlatformModel(entity));
        }
        return out;
    }

    public Platform getPlatformById(int id) {
        Optional<Platform> opt = repo.findById((Integer)id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    public List<PlatformModel> getPlatforms() {
        return convertEntityListToModelList(repo.findAll());
    }

    public void insertPlatform(PlatformModel platform) {
        repo.save(new Platform(platform));
    }

    public void deletePlatform(Platform platform) {
        repo.delete(platform);
    }*/
}
