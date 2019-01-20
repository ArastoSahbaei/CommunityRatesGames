package com.communityratesgames.transactions;

import com.communityratesgames.domain.*;
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
        //TODO: Use security to set verifiedBy
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
                getPlatformEntity(model.getPlatforms()),
                model.getDescription(),
                getUserEntity(model.getSubmittedBy()),
                Genre.valueOf(model.getGenre().toUpperCase())
        );
    }
    private Company getCompanyEntity(String companyname) {
        return em.createQuery("SELECT c FROM Company c WHERE c.companyName = :name",Company.class)
                .setParameter("name", companyname)
                .getSingleResult();
    }
    private List<Platform> getPlatformEntity(List<String> platformList){
        return em.createQuery("SELECT p FROM Platform p WHERE p.name IN :list",Platform.class)
                .setParameter("list", platformList)
                .getResultList();
    }
    private User getUserEntity(String user){
        return em.createQuery("SELECT u  FROM User u WHERE u.userName =:user",User.class)
                .setParameter("user",user)
                .getSingleResult();
    }
    private List<GameModel> convertListEntityToModel (List<UnverifiedGame> entityList) {
        for (UnverifiedGame g: entityList
             ) {
            System.out.println(g.getTitle());
            for (Platform p: g.getPlatforms()
                 ) {
                System.out.println(p.getName());
            }

        }
        return entityList.stream().map(GameModel::new).collect(Collectors.toList());
    }
}
