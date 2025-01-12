package org.example.models;

public class Comment {
    private String commentText;
    private String author;

    public Comment() {
    }

    public Comment(String commentText, String author) {
        this.commentText = commentText;
        this.author = author;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentText='" + commentText + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}