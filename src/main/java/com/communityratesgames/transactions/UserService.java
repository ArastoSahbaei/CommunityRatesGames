package com.communityratesgames.transactions;

import com.communityratesgames.domain.User;
import com.communityratesgames.user.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public User getUser(Long id) {
        Query q = em.createNativeQuery("SELECT * FROM user_entity WHERE id = ?", User.class)
            .setParameter(1, id);
        return (User)q.getSingleResult();
    }

    @Override
    public User register(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public AuthToken login(User user) {
        User u = (User)em.createQuery("SELECT u FROM User u WHERE u.email = :email")
            .setParameter("email", user.getEmail())
            .getSingleResult();
        String password = User.hashPassword(user.getPassword(), u.getPasswordHash());
        if (u.getPassword().equals(password)) {
            Long token = AuthToken.generateNewToken(u.getId());
            return new AuthToken(token, u.getId());
        } else {
            return null;
        }
    }

    public boolean logout(Long token) {
        return AuthToken.close(token);
    }
}
