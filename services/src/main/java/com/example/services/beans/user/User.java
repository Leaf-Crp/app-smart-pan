package com.example.services.beans.user;


import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String email;
    private String password;
    private String lastname;
    private String firstname;
    private int is_connected_pan;
    private String alarm_ended_recipe;
    private String alarm_ended_step;

    public User(){}

    public User(String email, String password, String lastname, String firstname) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.is_connected_pan = 1;
    }
    public User(String email, String lastname, String firstname) {
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getIs_connected_pan() {
        return is_connected_pan;
    }

    public void setIs_connected_pan(int is_connected_pan) {
        this.is_connected_pan = is_connected_pan;
    }

    public String getAlarm_ended_recipe() {
        return alarm_ended_recipe;
    }

    public void setAlarm_ended_recipe(String alarm_ended_recipe) {
        this.alarm_ended_recipe = alarm_ended_recipe;
    }

    public String getAlarm_ended_step() {
        return alarm_ended_step;
    }

    public void setAlarm_ended_step(String alarm_ended_step) {
        this.alarm_ended_step = alarm_ended_step;
    }
}