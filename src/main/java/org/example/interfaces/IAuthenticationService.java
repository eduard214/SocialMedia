package org.example.interfaces;

import org.example.models.User;
import org.example.models.UserService;

public interface IAuthenticationService {

    public void logoutStandardUser(UserService userService, String username);
    public void logoutAdminUser(UserService userService, String username);
    public boolean authenticateAdminUser(UserService userService, String username, String password);
    public boolean authenticateStandardUser(UserService userService, String username, String password);
}