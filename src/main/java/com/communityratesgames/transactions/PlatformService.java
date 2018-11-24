
package com.communityratesgames.transactions;

import com.communityratesgames.domain.PlatformEntity;

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
    public List<PlatformEntity> showAllPlatforms() {
        Query q = em.createNativeQuery("SELECT * FROM platform_entity", PlatformEntity.class);
        List<PlatformEntity> platforms = q.getResultList();
        return platforms;
    }
/*
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

    public void insertPlatform(PlatformModel platform) {
        repo.save(new PlatformEntity(platform));
    }

    public void deletePlatform(PlatformEntity platform) {
        repo.delete(platform);
    }*/
}
