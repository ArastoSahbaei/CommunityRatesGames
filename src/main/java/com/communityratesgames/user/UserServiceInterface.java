package com.communityratesgames.user;

import java.util.List;

public interface UserServiceInterface {

    List<UserModel> findAllUsers();

    UserModel findUserById(Long id);

    UserModel findUserByUserName(String username);

    UserEntity findUserByEmail(String email);

    void createNewUser(UserModel userModel);

}
