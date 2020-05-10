package com.example.services.api;

import com.example.services.beans.user.UserJSON;
import com.example.services.beans.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserCall {

    @POST("/check_login")
    Call<UserResponse> checkLogin(@Body UserJSON userJSON);

    @POST("/users")
    Call<String> create(@Body UserJSON userJSON);

}
