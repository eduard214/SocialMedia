package org.example.interfaces;

public interface IUser {
    String getUsername();

    // Returnează parola
    String getPassword();

    // Returnează tipul utilizatorului (Admin sau Standard)
    String getRole();
}
