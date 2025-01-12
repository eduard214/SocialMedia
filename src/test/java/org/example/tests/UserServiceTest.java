package org.example.tests;

import org.example.models.AdminUser;
import org.example.models.StandardUser;
import org.example.models.User;
import org.example.models.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void addUser() {
        StandardUser user = new StandardUser("testUser", "password", "test@example.com");
        assertTrue(userService.addUser(user));
        assertFalse(userService.addUser(user)); // Adding the same user again should fail
    }

    @Test
    void addAdminUser() {
        AdminUser adminUser = new AdminUser("adminUser", "password", "admin@example.com");
        assertTrue(userService.addAdminUser(adminUser));
        assertFalse(userService.addAdminUser(adminUser)); // Adding the same admin user again should fail
    }

    @Test
    void getAllUsers() {
        StandardUser user = new StandardUser("testUser", "password", "test@example.com");
        AdminUser adminUser = new AdminUser("adminUser", "password", "admin@example.com");
        userService.addUser(user);
        userService.addAdminUser(adminUser);
        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void findUserByUsername() {
        StandardUser user = new StandardUser("testUser", "password", "test@example.com");
        userService.addUser(user);
        Optional<User> foundUser = userService.findUserByUsername("testUser");
        assertTrue(foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getUsername());
    }

    @Test
    void updateUserEmail() {
        StandardUser user = new StandardUser("testUser", "password", "test@example.com");
        userService.addUser(user);
        assertTrue(userService.updateUserEmail("testUser", "new@example.com"));
        assertEquals("new@example.com", userService.findUserByUsername("testUser").get().getEmail());
    }

    @Test
    void deleteUser() {
        StandardUser user = new StandardUser("testUser", "password", "test@example.com");
        userService.addUser(user);
        assertTrue(userService.deleteUser("testUser"));
        assertFalse(userService.findUserByUsername("testUser").isPresent());
    }

    @Test
    void getAllAdminUsers() {
        AdminUser adminUser = new AdminUser("adminUser", "password", "admin@example.com");
        userService.addAdminUser(adminUser);
        List<AdminUser> adminUsers = userService.getAllAdminUsers();
        assertEquals(1, adminUsers.size());
        assertEquals("adminUser", adminUsers.get(0).getUsername());
    }

    @Test
    void findAdminUserByUsername() {
        AdminUser adminUser = new AdminUser("adminUser", "password", "admin@example.com");
        userService.addAdminUser(adminUser);
        Optional<AdminUser> foundAdminUser = userService.findAdminUserByUsername("adminUser");
        assertTrue(foundAdminUser.isPresent());
        assertEquals("adminUser", foundAdminUser.get().getUsername());
    }

    @Test
    void getAllStandardUsers() {
        StandardUser user = new StandardUser("testUser", "password", "test@example.com");
        userService.addUser(user);
        List<StandardUser> standardUsers = userService.getAllStandardUsers();
        assertEquals(1, standardUsers.size());
        assertEquals("testUser", standardUsers.get(0).getUsername());
    }

    @Test
    void loadUsers() {
        StandardUser user1 = new StandardUser("testUser1", "password", "test1@example.com");
        StandardUser user2 = new StandardUser("testUser2", "password", "test2@example.com");
        userService.LoadUsers(List.of(user1, user2));
        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void loadAdmins() {
        AdminUser adminUser1 = new AdminUser("adminUser1", "password", "admin1@example.com");
        AdminUser adminUser2 = new AdminUser("adminUser2", "password", "admin2@example.com");
        userService.LoadAdmins(List.of(adminUser1, adminUser2));
        List<AdminUser> adminUsers = userService.getAllAdminUsers();
        assertEquals(2, adminUsers.size());
    }
}