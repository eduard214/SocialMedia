package org.example.models;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.example.interfaces.IUser;

import java.util.Objects;


public class User implements IUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Email
    @NotNull
    private String email;
    private String role; // Admin SAU User
    private JavaTimeModule createdAt;
    private JavaTimeModule updatedAt;

    // Constructor implicit
    public User() {
    }

    // Constructor complet
    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role != null ? role : "USER"; // Default to "USER" if role is null
//        this.createdAt = new JavaTimeModule(); // Default current date
//        this.updatedAt = new JavaTimeModule(); // Default current date
    }

    // Getteri și Setteri
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return (password);
    }

    public void setPassword(String password) {
        //        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        this.password = encoder.encode(password);
        this.password = password;
    }

    public boolean checkPassword(String rawPassword) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder.matches(rawPassword, this.password);
        return this.password.equals(rawPassword);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public JavaTimeModule getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(JavaTimeModule createdAt) {
        this.createdAt = createdAt;
    }

    public JavaTimeModule getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(JavaTimeModule updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public Post createPost(String title, String content) {
        return new Post(title, content, this.username);
    }

    // Metode auxiliare
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}