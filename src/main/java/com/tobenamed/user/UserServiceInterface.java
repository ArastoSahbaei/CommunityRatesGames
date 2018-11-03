package com.tobenamed.user;

import java.util.List;

public interface UserServiceInterface {

    List<UserModel> findAllUsers();

    UserModel FindUserById(Long id);

    UserEntity FindUserByUserName(String username);

    UserModel createNewUser(UserModel userModel);

}