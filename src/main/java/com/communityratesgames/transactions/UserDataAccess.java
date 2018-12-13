package com.communityratesgames.transactions;

import com.communityratesgames.domain.User;
import com.communityratesgames.user.AuthToken;
import com.communityratesgames.util.JsonError;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserDataAccess {
    public abstract List<User> showAllUsers();
    public abstract User register(User user) throws JsonError;
    public abstract User login(User user);
    public boolean logout(Long token);
}
