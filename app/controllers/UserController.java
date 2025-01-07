//package controllers;
//
//import com.avaje.ebean.Ebean;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.google.inject.Inject;
//import models.User;
//import org.mindrot.jbcrypt.BCrypt;
//import play.mvc.*;
//
//public class UserController extends Controller {
//
//    // Signup method: this should automatically accept the request body
//    public Result signUp() {
//        JsonNode json = request().body().asJson(); // Get JSON body
//
//        String firstName = json.get("firstName").asText();
//        String lastName = json.get("lastName").asText();
//        String email = json.get("email").asText();
//        String password = json.get("password").asText();
//        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
//
//        // Check if the user already exists
//        if (Ebean.find(User.class).where().eq("email", email).findRowCount() > 0) {
//            return badRequest("User already exists");
//        }
//
//        // Save user
//        User user = new User();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setPassword(hashedPassword);
//        user.save();
//
//        return ok("Sign up successful");
//    }
//
//    // Login method
//    public Result login() {
//        JsonNode json = request().body().asJson(); // Get JSON body
//
//        String email = json.get("email").asText();
//        String password = json.get("password").asText();
//
//        User user = Ebean.find(User.class).where().eq("email", email).findUnique();
//
//        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
//            return unauthorized("Invalid email or password");
//        }
//
//        return ok("Login successful");
//    }
//
//    public Result testConnection() {
//        return ok("Connection successful");
//    }
//}

package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.libs.Json;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserController extends Controller {

    private static final String SECRET_KEY = "your_secret_key"; // Replace with a secure key
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Generate a JWT token
    private String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // Verify JWT token
    private String verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject(); // Returns the email if verification is successful
        } catch (JWTVerificationException e) {
            return null; // Token is invalid or expired
        }
    }

    // Signup method: this should automatically accept the request body
    public Result signUp() {
        JsonNode json = request().body().asJson(); // Get JSON body

        if (json == null) {
            return badRequest("Invalid JSON data");
        }

        String firstName = json.get("firstName").asText();
        String lastName = json.get("lastName").asText();
        String email = json.get("email").asText();
        String password = json.get("password").asText();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Check if the user already exists
        if (Ebean.find(User.class).where().eq("email", email).findRowCount() > 0) {
            return badRequest("User already exists");
        }

        // Save user
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.save();

        // Generate token
        String token = generateToken(email);

        // Create response
        ObjectNode response = Json.newObject();
        response.put("message", "Sign up successful");
        response.put("token", token);
        ObjectNode userJson = Json.newObject();
        userJson.put("firstName", user.getFirstName());
        userJson.put("lastName", user.getLastName());
        userJson.put("email", user.getEmail());
        userJson.put("createdAt", DATE_FORMAT.format(user.getCreatedAt()));
        userJson.put("updatedAt", DATE_FORMAT.format(user.getUpdatedAt()));
        response.set("user", userJson);

        return ok(response);
    }

    // Login method
    public Result login() {
        JsonNode json = request().body().asJson(); // Get JSON body

        if (json == null) {
            return badRequest("Invalid JSON data");
        }

        String email = json.get("email").asText();
        String password = json.get("password").asText();

        User user = Ebean.find(User.class).where().eq("email", email).findUnique();

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            return unauthorized("Invalid email or password");
        }

        // Generate token
        String token = generateToken(email);

        // Create response
        ObjectNode response = Json.newObject();
        response.put("message", "Login successful");
        response.put("token", token);
        ObjectNode userJson = Json.newObject();
        userJson.put("firstName", user.getFirstName());
        userJson.put("lastName", user.getLastName());
        userJson.put("email", user.getEmail());
        userJson.put("createdAt", DATE_FORMAT.format(user.getCreatedAt()));
        userJson.put("updatedAt", DATE_FORMAT.format(user.getUpdatedAt()));
        response.set("user", userJson);

        return ok(response);
    }

    // Protected /home route
    public Result home() {
        String authHeader = request().getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized("Authorization header is missing or invalid");
        }

        String token = authHeader.substring(7); // Extract token after "Bearer "
        String email = verifyToken(token);

        if (email == null) {
            return unauthorized("Invalid or expired token");
        }

        // Fetch user details
        User user = Ebean.find(User.class).where().eq("email", email).findUnique();

        if (user == null) {
            return unauthorized("User not found");
        }

        // Create response
        ObjectNode response = Json.newObject();
        response.put("message", "Welcome to the home page");
        ObjectNode userJson = Json.newObject();
        userJson.put("firstName", user.getFirstName());
        userJson.put("lastName", user.getLastName());
        userJson.put("email", user.getEmail());
        userJson.put("createdAt", DATE_FORMAT.format(user.getCreatedAt()));
        userJson.put("updatedAt", DATE_FORMAT.format(user.getUpdatedAt()));
        response.set("user", userJson);

        return ok(response);
    }

    public Result testConnection() {
        return ok("Connection successful");
    }
}
