package com.communityratesgames.user;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public UserModel findUserById(Long id) {
        UserEntity userEntity = userRepository.getOne(id);
        return new UserModel(userEntity);
    }

    public UserEntity findUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

 /*   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findUserByUserName(username);

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (userEntity != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
            builder.roles(userEntity.getRole());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
*/
}
