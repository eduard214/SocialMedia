package org.example.interfaces;

import org.example.models.AdminUser;
import org.example.models.StandardUser;

import java.util.List;

public interface IFileHandler {
    void saveStandardUsers(List<StandardUser> standardUsers);

    // Load standard users from a file
    List<StandardUser> loadStandardUsers();

    // Save admin users to a file
    void saveAdminUsers(List<AdminUser> adminUsers);

    // Load admin users from a file
    List<AdminUser> loadAdminUsers();
}
