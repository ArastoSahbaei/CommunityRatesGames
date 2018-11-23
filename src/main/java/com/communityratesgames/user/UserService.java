package com.communityratesgames.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.math.BigInteger;

@Service
public class UserService implements UserServiceInterface/*, UserDetailsService*/ {

    @Autowired
    private final UserRepository userRepository;

    private static Pattern passwordPattern;
    private static Pattern usernamePattern;

    static {
        passwordPattern = Pattern.compile("^[a-zA-Z0-9!#$*+-<>^~_]+$");
        usernamePattern = Pattern.compile("^[a-zA-Z0-9-_]+$");
    }

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

    public void createNewUser(UserModel userModel) {
        UserEntity user = new UserEntity(userModel);
        user.setUserCreated(Timestamp.from(Instant.now()));
        user.setRole("user");
        userRepository.save(user);
    }

    public List<UserModel> findAllUsers() {
        List<UserEntity> ListWithAllUsers = userRepository.findAll();
        return convertUserListToUserModelList(ListWithAllUsers);
    }

    public UserModel findUserById(Long id) {
        UserEntity userEntity = userRepository.getOne(id);
        return new UserModel(userEntity);
    }

    public UserModel findUserByUserName(String username) {
        UserEntity userEntity = userRepository.findUserByUserName(username);
        if (userEntity == null) {
            return null;
        } else {
            return new UserModel(userEntity);
        }
    }
    public UserEntity findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public ResponseEntity<?> checkCredentials(UserModel userModel) {
        String email = userModel.getEmail();
        String password = userModel.getPassword();

        System.out.println(email + " " + password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public UserEntity findUserByUserNameAndPassword(String username, String password) {
        UserEntity user = userRepository.findUserByUserName(username);
        if (user == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            password += user.getPasswordHash();
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] hash = md.digest();
            String pwd = String.format("%064x", new BigInteger(1, hash));
            System.out.printf("Comparing %s with %s\n", pwd, user.getPassword());
            if (!pwd.equals(user.getPassword())) {
                System.out.println("fail");
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println("success");
        return user;
    }
	/*
    @Override
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
	public ResponseEntity<?> validateUserConstraints(UserModel user){
        String username = user.getUserName();
        String password = user.getPassword();
        if (!usernamePattern.matcher(username).matches()){
            return new ResponseEntity<>("Username not valid" +
                    "Username may only be alpha numeric or contain - and _", HttpStatus.NOT_ACCEPTABLE);
        }
        if (username.length() < 3 || username.length() > 30){
            return new ResponseEntity<>("Username not valid" +
                    "Username must be between 3 to 30 characters", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!passwordPattern.matcher(password).matches()){
            return new ResponseEntity<>("Password not valid" +
                    "Password may only be alpha numeric or contain !#$*+-<>^~_", HttpStatus.NOT_ACCEPTABLE);
        }
        if (password.length() < 8 || password.length() > 32){
            return new ResponseEntity<>("Password not valid" +
                    "Password must be between 8 to 30 characters", HttpStatus.NOT_ACCEPTABLE);
        }
        if (findUserByUserName(username) != null){
            if (username.equalsIgnoreCase(findUserByUserName(username).getUserName())){
                return new ResponseEntity<>( HttpStatus.CONFLICT);
            }
            if (user.getEmail().equals(findUserByEmail(user.getEmail()).getEmail())){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }
        try {
            createNewUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
	}
}
