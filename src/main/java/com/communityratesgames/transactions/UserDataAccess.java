package com.communityratesgames.transactions;

import com.communityratesgames.domain.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserDataAccess {
    public abstract List<User> showAllUsers();
    public User register(String username, String email, String password);
    public abstract User login(String email, String password);
}
