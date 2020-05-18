package com.example.services.api;

import com.example.services.beans.user.User;
import com.example.services.beans.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserCall {

    @POST("/check_login")
    Call<UserResponse> checkLogin(@Body User user);

    @POST("/users")
    Call<String> create(@Body User user);

}
