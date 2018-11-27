package com.communityratesgames.transactions;

import com.communityratesgames.domain.User;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Stateless
@Default
public class UserService implements UserDataAccess {

    private final static Logger logger = Logger.getLogger(com.communityratesgames.transactions.UserService.class);

    @PersistenceContext(unitName = "communityratesgames")
    private EntityManager em;

    @Override
    public List<User> showAllUsers() {
        Query q = em.createNativeQuery("SELECT * FROM user_entity", User.class);
        List<User> users = q.getResultList();
        return users;
    }

    @Override
    public User register(String username, String email, String password) {
        User u = new User();
        u.setUserCreated(new Timestamp(System.currentTimeMillis()));
        u.setUserName(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole("user");
        em.persist(u);
        return u;
    }

    @Override
    public User login(String email, String password) {
        User u = (User)em.createQuery("SELECT u FROM User u WHERE u.email = :email")
            .setParameter("email", email)
            .getSingleResult();

        return (u.getPassword() == User.hashPassword(password, u.getPasswordHash())) ?
                u : null;
    }
/*
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

    private List<UserModel> convertUserListToUserModelList(List<User> userList) {
        List<UserModel> userModelList = new ArrayList<>();
        for (User userEntity : userList) {
            userModelList.add(new UserModel(userEntity));
        }
        return userModelList;
    }

    public void createNewUser(UserModel userModel) {
        User user = new User(userModel);
        user.setUserCreated(Timestamp.from(Instant.now()));
        user.setRole("user");
        userRepository.save(user);
    }

    public List<UserModel> findAllUsers() {
        List<User> ListWithAllUsers = userRepository.findAll();
        return convertUserListToUserModelList(ListWithAllUsers);
    }

    public UserModel findUserById(Long id) {
        User userEntity = userRepository.getOne(id);
        return new UserModel(userEntity);
    }

    public UserModel findUserByUserName(String username) {
        User userEntity = userRepository.findUserByUserName(username);
        if (userEntity == null) {
            return null;
        } else {
            return new UserModel(userEntity);
        }
    }
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findUserByUserNameAndPassword(String username, String password) {
        User user = userRepository.findUserByUserName(username);
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
        User userEntity = findUserByUserName(username);

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
/*
	public ResponseEntity<?> checkCredentials(UserModel userModel) {
	    System.out.println("IN HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEe");
	    return null;
    }

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
        if (password.length() < 8 || password.length() > 30){
            return new ResponseEntity<>("Password not valid" +
                    "Password must be between 8 to 30 characters", HttpStatus.NOT_ACCEPTABLE);
        }
        if (findUserByUserName(username) != null){
            if (username.equalsIgnoreCase(findUserByUserName(username).getUserName())){
                return new ResponseEntity<>("Username already exists", HttpStatus.NOT_ACCEPTABLE);
            }
            if (user.getEmail().equals(findUserByEmail(user.getEmail()).getEmail())){
                return new ResponseEntity<>("Email already exists", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        try {
            createNewUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
	}*/
}
