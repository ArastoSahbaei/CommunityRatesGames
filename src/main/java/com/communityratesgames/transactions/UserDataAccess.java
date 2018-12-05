package com.communityratesgames.transactions;

import com.communityratesgames.domain.User;
import com.communityratesgames.user.AuthToken;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserDataAccess {
    public abstract List<User> showAllUsers();
    public User getUser(Long id);
    public abstract User register(User user);
    public abstract AuthToken login(User user);
    public boolean logout(Long token);
}
