package com.example.smiling;

import java.io.Serializable;

public class User implements Serializable {
    private String yourName;
    private String email;
    private String password;
    private int unhappy;
    private int normal;
    private int happy;

    public User(String yourName, String email, String password) {
        this.yourName = yourName;
        this.email = email;
        this.password = password;
        this.unhappy = 0;
        this.normal = 0;
        this.happy = 0;
    }

    public User() {
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
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

    public int getUnhappy() {
        return unhappy;
    }

    public void setUnhappy(int unhappy) {
        this.unhappy = unhappy;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    @Override
    public String toString() {
        return "User{" +
                "yourName='" + yourName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", unhappy=" + unhappy +
                ", normal=" + normal +
                ", happy=" + happy +
                '}';
    }
}
