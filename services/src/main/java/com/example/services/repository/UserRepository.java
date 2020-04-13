package com.example.services.repository;

import com.example.services.api.UserCall;
import com.example.services.beans.User.UserJSON;
import com.example.services.beans.User.UserResponse;

import retrofit2.Call;
import retrofit2.Retrofit;

public class UserRepository extends BaseRepository {

    public Call<UserResponse> checkLogin(UserJSON user) {
        Retrofit retrofit = this.getRetrofit();
        UserCall userCall = retrofit.create(UserCall.class);
        Call<UserResponse> call = userCall.checkLogin(user);
        return call;
    }

}
