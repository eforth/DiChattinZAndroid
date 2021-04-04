package com.vectorjm.dichattinzandroid;

import java.time.LocalDateTime;

public abstract class ChatBout {
    private String userId;
    private LocalDateTime timestamp;
    private String description;
    private String type;
    private int comments;
    private int likes;
    private String Id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatBout chatBout = (ChatBout) o;

        return Id.equals(chatBout.Id);
    }

    @Override
    public int hashCode() {
        return Id.hashCode();
    }

    @Override
    public String toString() {
        return "ChatBout{" +
                "userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", comments=" + comments +
                ", likes=" + likes +
                ", Id='" + Id + '\'' +
                '}';
    }
}
