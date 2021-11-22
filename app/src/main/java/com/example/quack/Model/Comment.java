package com.example.quack.Model;

public class Comment {
    private String commentBody;
    private long commentedAt;
    private String CommentedBy;

    public Comment() {
    }


    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public long getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(long commentedAt) {
        this.commentedAt = commentedAt;
    }

    public String getCommentedBy() {
        return CommentedBy;
    }

    public void setCommentedBy(String commentedBy) {
        CommentedBy = commentedBy;
    }
}
