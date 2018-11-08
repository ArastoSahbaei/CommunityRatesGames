package com.communityratesgames.user;

import java.util.List;

public interface UserServiceInterface {

    List<UserModel> findAllUsers();

    UserModel findUserById(Long id);

    UserEntity findUserByUserName(String username);

    UserModel createNewUser(UserModel userModel);

}
