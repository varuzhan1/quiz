package com.egstestmyquizi.demo.model.persistence;


import javax.persistence.*;


@Entity
@Table

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column
    private String userName;

    @Column
    private String name;

    @Column
    private String surName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean isEnable;

    @Column
    private Integer points = 0;

    @Column
    private String token;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;


    public User() {
    }

    public User(String userName, String name, String surName, String email, String password, boolean isEnable) {
        this.userName = userName;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.isEnable = isEnable;
        this.role = Role.valueOf("USER");
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean enable) {
        isEnable = enable;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return "USER";
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
