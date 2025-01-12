package org.example.tests;

import org.example.models.Post;
import org.example.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        assertNull(user.getId());
    }

    @Test
    void getUsername() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void getPassword() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        assertEquals("password", user.getPassword());
    }

    @Test
    void setPassword() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void checkPassword() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        assertTrue(user.checkPassword("password"));
        assertFalse(user.checkPassword("wrongPassword"));
    }

    @Test
    void getEmail() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void setEmail() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    void getRole() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        assertEquals("USER", user.getRole());
    }

    @Test
    void setRole() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        user.setRole("ADMIN");
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    void getCreatedAt() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        assertNull(user.getCreatedAt());
    }


    @Test
    void getUpdatedAt() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        assertNull(user.getUpdatedAt());
    }

    @Test
    void createPost() {
        User user = new User("testUser", "password", "test@example.com", "USER");
        Post post = user.createPost("Test Title", "Test Content");
        assertNotNull(post);
        assertEquals("Test Title", post.getTitle());
        assertEquals("Test Content", post.getContent());
        assertEquals("testUser", post.getAuthor());
    }
}