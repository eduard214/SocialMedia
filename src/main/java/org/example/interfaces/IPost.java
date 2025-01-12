package org.example.interfaces;

import org.example.models.Comment;
import org.example.models.Like;

import java.util.ArrayList;

public interface IPost {
    String getTitle();

    void setTitle(String title);

    String getContent();

    void setContent(String content);

    String getAuthor();

    void setAuthor(String author);

    Like getLikes();

    void setLikes(Like likes);

    ArrayList<Comment> getComments();

    void setComments(ArrayList<Comment> comments);
}
