package com.example.services.api;

import com.example.services.beans.Recipe;
import com.example.services.beans.RecipeType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeTypesCall {
    @GET("recipe_types")
    Call<List<RecipeType>> allRecipeTypes();
}
