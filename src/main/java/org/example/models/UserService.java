package org.example.models;

import org.example.interfaces.IUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    // A mock user repository (in real applications, you would use something like JPA or Hibernate repository)
    private final List<User> users = new ArrayList<>();

    @Override
    public boolean addUser(StandardUser user) {
        if (user != null && !findUserByUsername(user.getUsername()).isPresent()) {
            users.add(user);
            System.out.println("User added: " + user.getUsername());
            return true;
        }
        System.out.println("Failed to add user: user was already added!.");
        return false;
    }

    @Override
    public boolean addAdminUser(AdminUser user) {
        if (user != null && !findAdminUserByUsername(user.getUsername()).isPresent()) {
            users.add(user);
            System.out.println("User added: " + user.getUsername());
            return true;
        }
        System.out.println("Failed to add admin user: admin user was already added!.");
        return false;
    }

    // Retrieve all users
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    // Update a user's email
    @Override
    public boolean updateUserEmail(String username, String newEmail) {
        Optional<User> userOptional = findUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmail(newEmail);
            System.out.println("Email updated for user: " + username);
            return true;
        } else {
            System.out.println("User not found with username: " + username);
            return false;
        }
    }

    // Delete a user by username
    @Override
    public boolean deleteUser(String username) {
        Optional<User> userOptional = findUserByUsername(username);
        if (userOptional.isPresent()) {
            users.remove(userOptional.get());
            System.out.println("User deleted: " + username);
            return true;
        } else {
            System.out.println("User not found with username: " + username);
            return false;
        }
    }

    // List all admin users
    @Override
    public List<AdminUser> getAllAdminUsers() {
        List<AdminUser> adminUsers = new ArrayList<>();
        for (User user : users) {
            if (user instanceof AdminUser) {
                adminUsers.add((AdminUser) user);
            }
        }
        return adminUsers;
    }

    @Override
    public Optional<AdminUser> findAdminUserByUsername(String username) {
        return users.stream()
                .filter(user -> user instanceof AdminUser) // Filtrăm utilizatorii de tip AdminUser
                .map(user -> (AdminUser) user) // Convertim la AdminUser
                .filter(adminUser -> adminUser.getUsername().equals(username)) // Verificăm username-ul
                .findFirst(); // Returnăm primul rezultat găsit
    }

    // List all standard users
    @Override
    public List<StandardUser> getAllStandardUsers() {
        List<StandardUser> standardUsers = new ArrayList<>();
        for (User user : users) {
            if (user instanceof StandardUser) {
                standardUsers.add((StandardUser) user);
            }
        }
        return standardUsers;
    }

    @Override
    public void LoadUsers(List<StandardUser> users) {
        this.users.addAll(users);
    }

    @Override
    public void LoadAdmins(List<AdminUser> users) {
        this.users.addAll(users);
    }
}