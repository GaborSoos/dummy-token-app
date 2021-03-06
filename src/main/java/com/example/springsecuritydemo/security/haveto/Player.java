package com.example.springsecuritydemo.security.haveto;

import javax.persistence.*;


@Entity(name = "players")
public class Player {

    @Id
    private int id;
    @Column(length = 50)
    private String username;
    private String password;
    @OneToOne(mappedBy = "player")
    private Kingdom kingdom;
    private String avatar;
    private Integer points;

    public Player(String username, String password, Kingdom kingdom, String avatar, Integer points) {
        this.username = username;
        this.password = password;
        this.kingdom = kingdom;
        this.avatar = avatar;
        this.points = points;
    }

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
