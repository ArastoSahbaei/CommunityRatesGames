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
    public abstract boolean logout(Long token);
    public abstract User detailsAboutAUser(String user);
    public abstract Boolean deleteAUser(User user);
    public abstract Integer updateAUser(User user);
}
