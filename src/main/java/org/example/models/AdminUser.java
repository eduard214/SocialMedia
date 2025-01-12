package org.example.models;

import java.time.LocalDateTime;
import java.util.List;

public class AdminUser extends User {
    private String adminRole;
    private LocalDateTime lastLogin;

    public AdminUser() {
        super(); // Apelăm constructorul de bază din `User`
        this.adminRole = "ADMIN"; // Rolul implicit
    }

    public AdminUser(String username, String password, String email) {
        super(username, password, email, "ADMIN");
        this.adminRole = "SuperAdmin"; // Poți personaliza acest rol dacă este nevoie
//        this.lastLogin = LocalDateTime.now();
    }

    // Getter pentru rolul de admin
    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    // Getter pentru ultima autentificare
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    // Funcție specifică adminului
    public void resetUserPassword(User user, String newPassword) {
        if (user != null) {
            user.setPassword(newPassword); // Adminul poate reseta parola oricărei utilizator
            System.out.println("Parola utilizatorului " + user.getUsername() + " a fost resetată!");
        }
    }

    // Exemplu de acțiuni suplimentare ale adminului
    public void banUser(User user) {
        if (user != null) {
            System.out.println("Utilizatorul " + user.getUsername() + " a fost blocat de adminul " + this.getUsername());
        }
    }

    public void unbanUser(User user) {
        if (user != null) {
            System.out.println("Utilizatorul " + user.getUsername() + " a fost deblocat de adminul " + this.getUsername());
        }
    }

    public void deletePost(List<Post> post, Post postToDelete) {
        if (post != null) {
            post.remove(postToDelete);
            System.out.println("Postarea a fost ștearsă de adminul " + this.getUsername());
        }
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "adminRole='" + adminRole + '\'' +
                ", lastLogin=" + lastLogin +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}