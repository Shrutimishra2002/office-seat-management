package services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.avaje.ebean.Ebean;
import com.smartcoin.db.services.ModelServiceImpl;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserServiceImpl extends ModelServiceImpl<Long, User> implements UserService {
    private static final String SECRET_KEY = "your_secret_key"; // Replace with a secure key
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    @Override
    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    @Override
    public String verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject(); // Returns the email if verification is successful
        } catch (JWTVerificationException e) {
            return null; // Token is invalid or expired
        }
    }

    @Override
    public boolean isUserExist(String email) {
        return Ebean.find(User.class).where().eq("email", email).findRowCount() > 0;

    }

    @Override
    public User createUser(String firstName, String lastName, String email, String hashedPassword){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.save();

        return user;
    }

    @Override
    public User authenticateUser(String email, String password) {
        User user = findUserByEmail(email);
        if (user != null || BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;

    }

    @Override
    public User findUserByEmail(String email){
       return findByField("email", email).get(0);
    }

    @Override
    public List<User> findAllUsers() {
        return getFinder().all();
    }

}
