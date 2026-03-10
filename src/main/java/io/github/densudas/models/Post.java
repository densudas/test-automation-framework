package io.github.densudas.models;

/**
 * Model class representing a Post object for serialization and deserialization examples.
 * This class is used with Rest Assured to demonstrate JSON conversion.
 */
public class Post {
    private Integer id;
    private String title;
    private String body;
    private Integer userId;

    // Default constructor required for deserialization
    public Post() {
    }

    // Constructor with all fields for easy object creation
    public Post(Integer id, String title, String body, Integer userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    // Getters and setters required for serialization/deserialization
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                '}';
    }
}
