package com.example.miniproject_orifrommer;

public class User {
    private long id;
    private String name;
    private String pass;

    public User() {
    }

    public User(long id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User:\t" + this.id + ", " + this.name + ", " + this.pass;
    }
}
