package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Like {
    private List<String> usernames;
    private int likes;

    // Default constructor
    public Like() {
        this.usernames = new ArrayList<>();
        likes = 0;
    }

    // Parameterized constructor
    public Like(List<String> usernames) {
        this.usernames = usernames;
    }

    public void giveLike(String username) {
        if (username.contains(username)) {
            System.out.println("You have already liked this post.");
            return;
        }
        this.usernames.add(username);
        this.likes++;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    // Method to add a username to the list
    public void addUsername(String username) {
        this.usernames.add(username);
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    @Override
    public String toString() {
        return "Like{" +
                "usernames=" + usernames +
                '}';
    }
}