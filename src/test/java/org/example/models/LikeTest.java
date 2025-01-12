package org.example.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LikeTest {

    @Test
    void giveLike() {
        Like like = new Like();
        like.giveLike("user1");
        assertEquals(1, like.getLikes());
        assertTrue(like.getUsernames().contains("user1"));

        // Test giving like again by the same user
        like.giveLike("user1");
        assertEquals(1, like.getLikes()); // Likes count should not increase
        assertEquals(1, like.getUsernames().size()); // Usernames list should not have duplicates

        // Test giving like by another user
        like.giveLike("user2");
        assertEquals(2, like.getLikes());
        assertTrue(like.getUsernames().contains("user2"));
    }
}