package com.example.services.api;

import com.example.services.beans.addrecipe.RecipeJSON;
import com.example.services.beans.Recipe;
import com.example.services.beans.recipe.Recipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RecipesCall {

    @GET("recipes")
    Call<List<Recipe>> allRecipes();

    @GET("recipes/users")
    Call<Recipes> ownRecipes();

    @POST("/recipes")
    Call<RecipeJSON> saveRecipe(@Body RecipeJSON transformRecipeToRecipeJSONRequest);
}
