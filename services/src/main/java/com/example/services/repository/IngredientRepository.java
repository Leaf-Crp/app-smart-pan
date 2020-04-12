package com.example.services.repository;

import com.example.services.api.IngredientsCall;
import com.example.services.api.config.Config;
import com.example.services.beans.Ingredient.Ingredient;
import com.example.services.beans.Ingredient.Ingredients;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientRepository {
    private ArrayList<Ingredient> ingredients;

    public Call<Ingredients> allIngredientsQuery() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IngredientsCall ingredientsCall = retrofit.create(IngredientsCall.class);

        Call<Ingredients> call = ingredientsCall.getIngredients();
        return call;
    }
}
