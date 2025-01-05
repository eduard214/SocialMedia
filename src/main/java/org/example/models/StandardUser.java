package org.example.models;

import java.time.LocalDateTime;

public class StandardUser extends User {
    private LocalDateTime lastActivity;

    public StandardUser() {
        super(); // Invokes `User`'s no-argument constructor
//        this.lastActivity = LocalDateTime.now(); // Sets the last activity to the current time
    }

    // Constructor with parameters
    public StandardUser(String username, String password, String email) {
        super(username, password, email, "USER"); // Sets the role to "USER" by default
//        this.lastActivity = LocalDateTime.now(); // Sets the last activity to the current time
    }

    // Getter for lastActivity
    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    // Setter for lastActivity
    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    // Custom methods for a standard user
    public void updateActivity() {
        this.lastActivity = LocalDateTime.now(); // Updates the last activity time
    }

    @Override
    public String toString() {
        return "StandardUser{" +
                "lastActivity=" + lastActivity +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}