package com.communityratesgames.transactions;

import com.communityratesgames.domain.Company;
import com.communityratesgames.domain.Game;
import com.communityratesgames.domain.Platform;
import com.communityratesgames.domain.UnverifiedGame;
import com.communityratesgames.model.GameModel;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Default
public class UnverifiedGameService implements UnverifiedGameDataAccess{

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public void addUnverifiedGame(GameModel model) {
        em.persist(createUnverifiedGameEntity(model));
    }

    //TODO: ADD ADMIN SECURITY
    @Override
    public void deleteUnverifiedGame(Long id) {
        em.remove(em.find(UnverifiedGame.class, id));
    }

    @Override
    public void verifyGame(Long id) {
        UnverifiedGame game = em.find(UnverifiedGame.class,id);
        em.persist(new Game(game,false));
        em.remove(game);
    }

    @Override
    public List<GameModel> getAllUnverifiedGames() {
        return convertListEntityToModel(
                em.createQuery("SELECT g FROM UnverifiedGame g", UnverifiedGame.class)
                        .getResultList()
        );
    }

    private UnverifiedGame createUnverifiedGameEntity(GameModel model) {
        return new UnverifiedGame(
                model.getReleaseDate(),
                model.getTitle(),
                getCompanyEntity(model.getCompany()),
                getPlatformEntity(model.getPlatforms())
        );
    }
    private Company getCompanyEntity(String companyname) {
        return em.createQuery("SELECT c FROM Company c WHERE c.companyName = :name",Company.class)
                .setParameter("name", companyname)
                .getSingleResult();
    }
    private List<Platform> getPlatformEntity(List<String> platformList){
        List<Platform> derp = em.createQuery("SELECT p FROM Platform p WHERE p.name IN :list",Platform.class)
                .setParameter("list", platformList)
                .getResultList();
        for (Platform p:derp
             ) {
            System.out.println("####PLATFORMENTITY####");
            System.out.println(p.getName());
        }
        return derp;
    }
    private List<GameModel> convertListEntityToModel (List<UnverifiedGame> entityList) {
        for (UnverifiedGame g: entityList
             ) {
            System.out.println("########");
            System.out.println(g.getTitle());
            for (Platform p: g.getPlatforms()
                 ) {
                System.out.println(p.getName());
            }

        }
        return entityList.stream().map(GameModel::new).collect(Collectors.toList());
    }
}
