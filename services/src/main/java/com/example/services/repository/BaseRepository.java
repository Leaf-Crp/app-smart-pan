package com.example.services.repository;

import com.example.services.api.config.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRepository {
    public Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
