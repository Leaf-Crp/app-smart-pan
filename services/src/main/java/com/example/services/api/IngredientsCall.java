package com.example.services.api;

import com.example.services.beans.Ingredient.Ingredients;
import com.example.services.beans.RecipeType.RecipeTypes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IngredientsCall {
    @GET("ingredients")
    Call<Ingredients> getIngredients();
}
