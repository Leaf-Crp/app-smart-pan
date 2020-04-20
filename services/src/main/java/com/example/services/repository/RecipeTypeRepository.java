package com.example.services.repository;

import com.example.services.api.RecipeTypesCall;
import com.example.services.api.config.Config;
import com.example.services.beans.recipetype.RecipeType;
import com.example.services.beans.recipetype.RecipeTypes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeTypeRepository {
    private ArrayList<RecipeType> recipeTypes;

    public Call<RecipeTypes> allRecipeTypesQuery() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipeTypesCall recipeTypesCall = retrofit.create(RecipeTypesCall.class);

        Call<RecipeTypes> call = recipeTypesCall.getRecipeTypes();
        return call;
    }
}
