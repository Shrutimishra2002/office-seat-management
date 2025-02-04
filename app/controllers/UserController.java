
package controllers;
import com.smartcoin.utils.JsonUtil;
import models.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;
import play.Configuration;
import play.libs.Json;
import play.mvc.*;
import services.UserService;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;


public class UserController extends Controller {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final UserService userService;

    @Inject
    public UserController(UserService userService, Configuration configuration){

        this.userService = userService;
        this.configuration = configuration;
    }


    public Result signUp() {
        JsonNode json = request().body().asJson();

        if (json == null) {
            return badRequest("Invalid JSON data");
        }

        try {
            String firstName = json.get("firstName").asText();
            String lastName = json.get("lastName").asText();
            String email = json.get("email").asText();
            String password = json.get("password").asText();
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            if (userService.isUserExist(email)){
                return badRequest("User already exists");
            }

            User user = userService.createUser(firstName, lastName, email, hashedPassword);
            String token = userService.generateToken(email);

            ObjectNode response = Json.newObject();
            response.put("message", "Sign up successful");
            response.put("token", token);
            response.set("user", createUserJson(user));

            return ok(response);

        } catch (Exception e) {
            return internalServerError("Error occurred during sign up: " + e.getMessage());
        }
    }


    @Inject
    private Configuration configuration;

    public Result login() {
        JsonNode json = request().body().asJson();

        if (json == null) {
            return badRequest("Invalid JSON data");
        }

        try {
            String email = json.get("email").asText();
            String password = json.get("password").asText();

            User user = userService.authenticateUser(email, password);

            if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
                return unauthorized("Invalid email or password");
            }

            List<Long> adminUserIds = configuration.getLongList("admin-user-list");
            long userId = user.getId();

            String role = adminUserIds.contains(userId) ? "admin" : "user";
            String token = userService.generateToken(email);

            ObjectNode response = Json.newObject();
            response.put("message", "Login successful");
            response.put("token", token);
            response.put("role", role);
            response.put("username", user.getFirstName() + " " + user.getLastName());
            response.put("userId", userId);
            response.set("user", createUserJson(user));
            return ok(response);
        } catch (Exception e) {
            return internalServerError("Error occurred during login: " + e.getMessage());
        }
    }


    public Result home() {
        String authHeader = request().getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized("Authorization header is missing or invalid");
        }
        try{
            String token = authHeader.substring(7); // Extract token after "Bearer "
            String email = userService.verifyToken(token);

            if (email == null) {
                return unauthorized("Invalid or expired token");
            }
            User user= userService.findUserByEmail(email) ;
            if (user == null) {
                return unauthorized("User not found");
            }

            ObjectNode response = Json.newObject();
            response.put("message", "Welcome to the home page");
            response.set("user", createUserJson(user));

            return ok(response);
        }
        catch (Exception e) {
            return internalServerError("Error occurred while processing the request: " + e.getMessage());
        }

    }

    private ObjectNode createUserJson(User user) {
        ObjectNode userJson = Json.newObject();
        userJson.put("firstName", user.getFirstName());
        userJson.put("lastName", user.getLastName());
        userJson.put("email", user.getEmail());
        userJson.put("createdAt", DATE_FORMAT.format(user.getCreatedAt()));
        userJson.put("updatedAt", DATE_FORMAT.format(user.getUpdatedAt()));
        return userJson;
    }

    public Result testConnection() {
        return ok("Hii Shruti Mishra");
    }

    public Result listUsersNotAdmin() {
        try{
            List<User> users=userService.findAllUsers();
            List<Long> adminUserIds = configuration.getLongList("admin-user-list");
            List<User> nonAdminUsers = users.stream()
                    .filter(user -> !adminUserIds.contains(user.getId()))
                    .collect(Collectors.toList());
            return ok(JsonUtil.toJson(nonAdminUsers));
        }
        catch (Exception e) {
            return internalServerError("Error occurred during list users: " + e.getMessage());
        }

    }
}
