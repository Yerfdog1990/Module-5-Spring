package springsecurity.architecture.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserAdminController {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserAdminController(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder){
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. Create a new user
    /*
    createUser(UserDetails user)
    Inserts into users and authorities tables.
    Example SQL executed:
    INSERT INTO users (username, password, enabled) VALUES (?, ?, ?);
    INSERT INTO authorities (username, authority) VALUES (?, ?);
     */
    @PostMapping("/create")
    public String createUser(@RequestParam String username,@RequestParam  String password){
        if(userDetailsManager.userExists(username)){
            return "User already exists";
        }
        UserDetails user = User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
        return "User " + username + " created";
    }
    // 2. Update an existing user (e.g. change a role)
    /*
    updateUser(UserDetails user)
    Updates password, roles, enabled flag.
    Deletes old roles and reinserts new ones.
    Example SQL executed:
    UPDATE users SET password=?, enabled=? WHERE username=?;
    DELETE FROM authorities WHERE username=?;
    INSERT INTO authorities (username, authority) VALUES (?, ?);
     */
    @PostMapping("/update")
    public String updateUser(@RequestParam String username,@RequestParam  String newPassword){
        if(!userDetailsManager.userExists(username)){
            return "User does not exist";
        }
        UserDetails user = User.withUsername(username)
                .password(passwordEncoder.encode(newPassword))
                .roles("USER", "EDITOR") // add editor role
                .build();
        userDetailsManager.updateUser(user);
        return "User " + username + " updated";
    }
    // 3. Delete an existing user
    /*
    deleteUser(String username)
    Removes user and associated roles.
    Example SQL executed:
    DELETE FROM authorities WHERE username=?;
    DELETE FROM users WHERE username=?;
     */
    @PostMapping("/{username}")
    public String deleteUser(@PathVariable String username){
        if(!userDetailsManager.userExists(username)){
            return "User does not exist";
        }
        userDetailsManager.deleteUser(username);
        return "User " + username + " deleted";
    }
    // 4. Change the password (for the current logged-in user)
    /*
    changePassword(String oldPassword, String newPassword)
    Retrieves the currently logged-in user from SecurityContextHolder.
    Validates the old password with PasswordEncoder.matches().
    Updates the password in users table.
     */
    @PostMapping("/change-password")
    public String changePassword(@RequestParam String oldPassword,@RequestParam String newPassword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserDetails)){
            return "Unauthorized: Please authenticate before changing password.";
        }
        // Do NOT manually compare against the principal's password (it may be cleared)
        // Let the configured AuthenticationManager inside UserDetailsManager perform re-authentication
        userDetailsManager.changePassword(oldPassword, passwordEncoder.encode(newPassword));
        return "Password changed successfully";
    }
    // Check if the user exists
    /*
    userExists(String username)
    Queries the users table:
    SELECT username FROM users WHERE username=?;
     */
    @GetMapping("/exists/{username}")
    public String userExists(@PathVariable String username){
        boolean exists = userDetailsManager.userExists(username);
        return exists ? "User exists" : "User does not exist";
    }
}
