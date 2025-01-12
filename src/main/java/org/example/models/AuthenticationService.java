package org.example.models;

import org.example.interfaces.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class AuthenticationService implements org.example.interfaces.IAuthenticationService {
    
    public AuthenticationService() {};
    // Authenticate a standard user by username and password
    @Override
    public boolean authenticateStandardUser(IUserService userService, String username, String password) {
        Optional<User> userOptional = userService.findUserByUsername(username);
        if (userOptional.isPresent()) {
            StandardUser user = (StandardUser) userOptional.get();
            if (user.getPassword().equals(password)) { // Simple password match
                user.updateActivity(); // Update user's last activity timestamp
                System.out.println("Standard user authenticated successfully: " + username);
                return true;
            } else {
                System.out.println("Invalid password for standard user: " + username);
            }
        } else {
            System.out.println("Standard user not found: " + username);
        }
        return false;
    }

    // Authenticate an admin user by username and password
    @Override
    public boolean authenticateAdminUser(IUserService userService, String username, String password) {
        Optional<AdminUser> userOptional = userService.findAdminUserByUsername(username);
        if (userOptional.isPresent()) {
            AdminUser user = userOptional.get();
            if (user.getPassword().equals(password)) { // Simple password match
                user.setLastLogin(LocalDateTime.now()); // Update admin's last login timestamp
                System.out.println("Admin user authenticated successfully: " + username);
                return true;
            } else {
                System.out.println("Invalid password for admin user: " + username);
            }
        } else {
            System.out.println("Admin user not found: " + username);
        }
        return false;
    }

    // Check if a user is an admin
    public boolean isAdmin(UserService userService, String username) {
        return userService.findAdminUserByUsername(username).isPresent();
    }

    // Check if a user is a standard user
    public boolean isStandardUser(UserService userService, String username) {
        return userService.findUserByUsername(username).isPresent();
    }

    // Logout for a standard user
    @Override
    public void logoutStandardUser(UserService userService, String username) {
        Optional<User> userOptional = userService.findUserByUsername(username);
        if (userOptional.isPresent()) {
            StandardUser user = (StandardUser) userOptional.get();
            user.updateActivity(); // Update the last activity time during logout
            System.out.println("Standard user logged out successfully: " + username);
        } else {
            System.out.println("Standard user not found: " + username);
        }
    }

    // Logout for an admin user
    @Override
    public void logoutAdminUser(UserService userService, String username) {
        Optional<AdminUser> userOptional = userService.findAdminUserByUsername(username);
        if (userOptional.isPresent()) {
            AdminUser user = userOptional.get();
            user.setLastLogin(LocalDateTime.now()); // Update the admin's last login timestamp
            System.out.println("Admin user logged out successfully: " + username);
        } else {
            System.out.println("Admin user not found: " + username);
        }
    }
}