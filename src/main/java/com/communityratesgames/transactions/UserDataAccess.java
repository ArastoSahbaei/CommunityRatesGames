package com.communityratesgames.transactions;

import com.communityratesgames.domain.User;
import com.communityratesgames.user.AuthToken;
import com.communityratesgames.util.FileLimitReachedException;
import com.communityratesgames.util.InvalidFileFormatException;
import com.communityratesgames.util.JsonError;

import javax.ejb.Local;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;

@Local
public interface UserDataAccess {
    public abstract List<User> showAllUsers();
    public abstract User register(User user) throws JsonError;
    public abstract Long login(User user);
    public abstract User getUserToken(Long token);
    public abstract boolean logout(Long token);
    public abstract User detailsAboutAUser(String user);
    public abstract Boolean deleteAUser(User user);
    public abstract Integer updateAUser(User user);
    public User setUserAvatar(User user, InputStream image) throws IOException, FileLimitReachedException, InvalidFileFormatException;
    public File getUserAvatar(User user) throws IOException;
}
