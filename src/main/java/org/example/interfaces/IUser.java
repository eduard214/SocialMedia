package org.example.interfaces;

import org.example.models.Post;

public interface IUser {
    String getUsername();

    // Returnează parola
    String getPassword();

    // Returnează tipul utilizatorului (Admin sau Standard)
    String getRole();

    Post createPost(String title, String content);
}
