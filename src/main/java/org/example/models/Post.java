package org.example.models;

import org.example.interfaces.IPost;

import java.util.ArrayList;

public class Post implements IPost {
    private String title;
    private String content;
    private String author;
    private Like likes;
    private ArrayList<Comment> comments;

    public Post() {
    }

    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        likes = new Like();
        comments = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public ArrayList<Comment> getComments() {
        return comments;
    }

    @Override
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public Like getLikes() {
        return likes;
    }

    @Override
    public void setLikes(Like likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
