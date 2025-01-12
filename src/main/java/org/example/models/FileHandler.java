package org.example.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.interfaces.IFileHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler implements IFileHandler {
    private static final String STANDARD_USERS_FILE = "standard_users.json";
    private static final String ADMIN_USERS_FILE = "admin_users.json";
    private static final String POSTS_FILE = "posts.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Save standard users to a JSON file
    @Override
    public void saveStandardUsers(List<StandardUser> standardUsers) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(STANDARD_USERS_FILE), standardUsers);
            System.out.println("Standard users saved to " + STANDARD_USERS_FILE);
        } catch (IOException e) {
            System.err.println("Failed to save standard users: " + e.getMessage());
        }
    }

    // Load standard users from a JSON file
    @Override
    public List<StandardUser> loadStandardUsers() {
        try {
            File file = new File(STANDARD_USERS_FILE);
            if (file.exists()) {
                if (file.length() == 0) {
                    return new ArrayList<>();
                }
                return objectMapper.readValue(file, new TypeReference<List<StandardUser>>() {
                });
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            System.err.println("Failed to load standard users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Save admin users to a JSON file
    @Override
    public void saveAdminUsers(List<AdminUser> adminUsers) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ADMIN_USERS_FILE), adminUsers);
            System.out.println("Admin users saved to " + ADMIN_USERS_FILE);
        } catch (IOException e) {
            System.err.println("Failed to save admin users: " + e.getMessage());
        }
    }

    // Load admin users from a JSON file
    @Override
    public List<AdminUser> loadAdminUsers() {
        try {
            File file = new File(ADMIN_USERS_FILE);
            if (file.exists()) {
                if (file.length() == 0) {
                    return new ArrayList<>();
                }
                return objectMapper.readValue(file, new TypeReference<List<AdminUser>>() {
                });
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            System.err.println("Failed to load admin users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void savePosts(List<Post> posts) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(POSTS_FILE), posts);
            System.out.println("Posts saved to posts.json");
        } catch (IOException e) {
            System.err.println("Failed to save posts: " + e.getMessage());
        }
    }

    @Override
    public List<Post> loadPosts() {
        try {
            File file = new File(POSTS_FILE);
            if (file.exists()) {
                if (file.length() == 0) {
                    return new ArrayList<>();
                }
                return objectMapper.readValue(file, new TypeReference<List<Post>>() {
                });
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            System.err.println("Failed to load posts: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}