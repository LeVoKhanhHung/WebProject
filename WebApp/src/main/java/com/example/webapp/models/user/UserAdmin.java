package com.example.webapp.models.user;

public class UserAdmin {
    private int id;
    private String userName;
    private String email;
    private int isActive;

    public UserAdmin(){}

    public UserAdmin(int id, String userName, String email, int isActive) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int isActive() {
        return isActive;
    }

    public void setActive(int isActive) {
        this.isActive = isActive;
    }
}
