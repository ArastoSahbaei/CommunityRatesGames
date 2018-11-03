package com.tobenamed.user;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInterface {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private List<UserModel> convertUserListToUserModelList(List<UserEntity> userList) {
        List<UserModel> userModelList = new ArrayList<>();
        for (UserEntity userEntity : userList) {
            userModelList.add(new UserModel(userEntity));
        }
        return userModelList;
    }

    public UserModel createNewUser(UserModel userModel) {
        UserEntity user = new UserEntity(userModel);
        return new UserModel(userRepository.save(user));
    }

    public List<UserModel> findAllUsers() {
        List<UserEntity> ListWithAllUsers = userRepository.findAll();
        return convertUserListToUserModelList(ListWithAllUsers);
    }

    public UserModel FindUserById(Long id) {
        UserEntity userEntity = userRepository.getOne(id);
        return new UserModel(userEntity);
    }

    public UserEntity FindUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }


}
