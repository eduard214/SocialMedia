package org.example.interfaces;

import org.example.models.AdminUser;
import org.example.models.StandardUser;
import org.example.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    // Găsirea unui utilizator standard după numele de utilizator (username)
    Optional<User> findUserByUsername(String username);

    // Găsirea unui utilizator administrator după numele de utilizator (username)
    Optional<AdminUser> findAdminUserByUsername(String username);

    // Adăugarea unui utilizator
    boolean addUser(StandardUser user);

    boolean addAdminUser(AdminUser user);

    // Ștergerea unui utilizator după username
    boolean deleteUser(String username);

    // Obținerea tuturor utilizatorilor
    List<User> getAllUsers();

    // Actualizarea unui utilizator (poate fi definită ca o suprascriere a unui utilizator)
    boolean updateUserEmail(String username, String newEmail);

    public List<StandardUser> getAllStandardUsers();

    public List<AdminUser> getAllAdminUsers();

    public void LoadUsers(List<StandardUser> users);

    public void LoadAdmins(List<AdminUser> admins);
}