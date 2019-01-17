package com.communityratesgames.transactions;

import com.communityratesgames.domain.User;
import com.communityratesgames.jms.JMSSender;
import com.communityratesgames.user.*;
import com.communityratesgames.util.JsonError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Stateless
@Default
public class UserService implements UserDataAccess {

    private final static Logger logger = Logger.getLogger(com.communityratesgames.transactions.UserService.class);

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public List<User> showAllUsers() {
        Query q = em.createNativeQuery("SELECT * FROM user_entity", User.class);
        List<User> users = q.getResultList();
        return users;
    }

    @Override
    public User register(User user) throws JsonError {
        if (em.createNativeQuery("SELECT * FROM user_entity where email = ?")
                .setParameter(1, user.getEmail())
                .getResultList().size() != 0) {
            throw new JsonError(3, "email already registered");
        }
        if (em.createNativeQuery("SELECT * FROM user_entity where userName = ?")
                .setParameter(1, user.getUserName())
                .getResultList().size() != 0) {
            throw new JsonError(4, "username already registered");
        }
        em.persist(user);
        return user;
    }

    @Override
    public Long login(User user) {
        try {
            User u = (User)em.createQuery("SELECT u FROM User u WHERE u.email = :email")
                .setParameter("email", user.getEmail())
                .getSingleResult();
            String password = User.hashPassword(user.getPassword(), u.getPasswordHash());
            if (u.getPassword().equals(password)) {
                Long token = AuthToken.generateNewToken(u.getId());
                return token;
            } else {
                return null;
            }
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getUserToken(Long token) {
        try {
            Long uid = AuthToken.getUserId(token);
            if (uid == -1L) {
                return null;
            }

            User u = (User)em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", uid)
                .getSingleResult();
            return u;
        } catch (NoResultException e) {
            return null;
        }
    }

    private User findUserId(User user) {
        User um = (User)em.createNativeQuery("SELECT * FROM user_entity WHERE email = :user", User.class)
                .setParameter("user", user.getEmail())
                .getSingleResult();
        return um;
    }

    @Override
    public Boolean deleteAUser(User user) {
        Integer success = em.createNativeQuery("DELETE FROM user_entity WHERE userName = :user", User.class)
                .setParameter("user", user.getUserName())
                .executeUpdate();

        if (success == 1 ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer updateAUser(User user) {
        User u = findUserId(user);
        Integer newUser = em.createNativeQuery("UPDATE user_entity SET userName = :user WHERE email = :email", User.class)
                .setParameter("user", user.getUserName())
                .setParameter("email", u.getEmail())
                .executeUpdate();
        System.out.println(newUser);
        return newUser;
    }

    @Override
    public User detailsAboutAUser(String user) {
        User u = (User)em.createNativeQuery("SELECT * FROM user_entity WHERE userName = :user", User.class)
                .setParameter("user", user)
                .getSingleResult();
        return u;
    }

    public boolean logout(Long token) {
        return AuthToken.close(token);
    }
}
