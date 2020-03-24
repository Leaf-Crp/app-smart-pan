package com.example.services.api;

import com.example.services.beans.Recipe;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesCall {
    @GET("recipes")
    Call<List<Recipe>> allRecipes();
}
