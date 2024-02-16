package com.example.adminapp.faculty;

public class Teacher {
    private String name;
    private String email;
    private String post;
    private String image;
    private String category;
    private String key;

    public Teacher() {
        // Default constructor required for calls to DataSnapshot.getValue(Teacher.class)
    }

    public Teacher(String name, String email, String post, String image, String category, String key) {
        this.name = name;
        this.email = email;
        this.post = post;
        this.image = image;
        this.category = category;
        this.key = key;
    }

    // Getters and setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
