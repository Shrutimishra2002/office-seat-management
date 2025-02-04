package services;

import com.google.inject.ImplementedBy;
import com.smartcoin.db.services.ModelService;
import models.User;
import services.impl.UserServiceImpl;

import java.util.List;

@ImplementedBy(UserServiceImpl.class)
public interface UserService extends ModelService<Long, User> {
     String generateToken(String email);
     String verifyToken(String token);
     boolean isUserExist(String email);
     User createUser(String firstName, String lastName, String email, String hashedPassword);
     User authenticateUser(String email, String hashedPassword);
     User findUserByEmail(String email);
     List<User> findAllUsers();
//     User authenticateAdminUser(String email, String hashedPassword);
}

