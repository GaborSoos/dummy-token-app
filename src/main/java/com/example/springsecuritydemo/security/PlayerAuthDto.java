package com.example.springsecuritydemo.security;

public class PlayerAuthDto {

    private String username;
    private String password;

    public PlayerAuthDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
