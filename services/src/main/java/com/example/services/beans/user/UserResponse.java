package com.example.services.beans.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
   public static String LOGIN_SUCCESSFULL = "login_successfull";
    public static String FAILED_CONNECTION = "incorrect_login_or_password";

    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

}
