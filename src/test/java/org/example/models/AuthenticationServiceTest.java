package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {
    private AuthenticationService authenticationService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService();
        userService = new UserService();
    }

    @Test
    void authenticateStandardUser() {
        StandardUser user = new StandardUser("testUser", "password", "test@example.com");
        userService.addUser(user);

        assertTrue(authenticationService.authenticateStandardUser(userService, "testUser", "password"));
        assertFalse(authenticationService.authenticateStandardUser(userService, "testUser", "wrongPassword"));
        assertFalse(authenticationService.authenticateStandardUser(userService, "nonExistentUser", "password"));
    }

    @Test
    void authenticateAdminUser() {
        AdminUser adminUser = new AdminUser("adminUser", "password", "admin@example.com");
        userService.addAdminUser(adminUser);

        assertTrue(authenticationService.authenticateAdminUser(userService, "adminUser", "password"));
        assertFalse(authenticationService.authenticateAdminUser(userService, "adminUser", "wrongPassword"));
        assertFalse(authenticationService.authenticateAdminUser(userService, "nonExistentAdminUser", "password"));
    }

    @Test
    void logoutStandardUser() {
        StandardUser user = new StandardUser("testUser", "password", "test@example.com");
        userService.addUser(user);

        authenticationService.logoutStandardUser(userService, "testUser");
        Optional<User> foundUser = userService.findUserByUsername("testUser");
        assertTrue(foundUser.isPresent());
    }

    @Test
    void logoutAdminUser() {
        AdminUser adminUser = new AdminUser("adminUser", "password", "admin@example.com");
        userService.addAdminUser(adminUser);

        authenticationService.logoutAdminUser(userService, "adminUser");
        Optional<AdminUser> foundAdminUser = userService.findAdminUserByUsername("adminUser");
        assertTrue(foundAdminUser.isPresent());
        assertNotNull(foundAdminUser.get().getLastLogin());
    }
}