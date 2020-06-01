package com.example.services.api.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {

    private final static String BASE_URL = "http://192.168.0.16:3001/";

    private static Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static String getUrl() {
        return BASE_URL;
    }

    public static Retrofit getRetrofit(){
        return RETROFIT;
    }
}
