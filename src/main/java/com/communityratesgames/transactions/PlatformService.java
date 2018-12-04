
package com.communityratesgames.transactions;

import com.communityratesgames.domain.Company;
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

    @Override
    public Platform createPlatform(String name, int releaseYear, Long companyId) {
        Platform platform;
        if (companyId != null) {
            Company company = (Company)em.createQuery("SELECT c FROM Company c WHERE c.id = :id")
                .setParameter("id", companyId)
                .getSingleResult();
            if (company == null) {
                return null;
            }
            platform = new Platform(name, releaseYear, company, null);
        } else {
            platform = new Platform(name, releaseYear, null, null);
        }

        em.persist(platform);
        return platform;
    }
/*
    @Autowired
    private PlatformRepository repo;

    private List<PlatformModel> convertEntityListToModelList(List<PlatformInterface> list) {
        List<PlatformModel> out = new ArrayList<>();
        for (PlatformInterface entity : list) {
            out.add(new PlatformModel(entity));
        }
        return out;
    }

    public PlatformInterface getPlatformById(int id) {
        Optional<PlatformInterface> opt = repo.findById((Integer)id);
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
        repo.save(new PlatformInterface(platform));
    }

    public void deletePlatform(PlatformInterface platform) {
        repo.delete(platform);
    }*/
}
