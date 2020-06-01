package com.example.services.beans.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.Date;

public class Message implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("id_recipe")
    @Expose
    private int id_recipe;
    @SerializedName("id_user")
    @Expose
    private int id_user;
    @SerializedName("date")
    @Expose
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId_recipe() {
        return id_recipe;
    }

    public int getId_user() {
        return id_user;
    }

    public Date getDate() {
        return date;
    }
}
