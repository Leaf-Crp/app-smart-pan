package com.example.services.api;

import com.example.services.beans.AddRecipe.RecipeJSON;
import com.example.services.beans.User.UserJSON;
import com.example.services.beans.User.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserCall {

    @POST("/check_login")
    Call<UserResponse> checkLogin(@Body UserJSON userJSON);

}
