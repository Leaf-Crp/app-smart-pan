package com.example.services.repository;

import com.example.services.api.RecipesCall;
import com.example.services.beans.AddRecipe.Recipe;
import com.example.services.beans.AddRecipe.RecipeJSON;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RecipeRepository extends BaseRepository {

    public Call<RecipeJSON> saveRecipe(Recipe recipe) {
        RecipeJSON recipeJson = new RecipeJSON(recipe.getLabel(), recipe.getPrivate(), "you",
                1,1, recipe.getSteps());
        Retrofit retrofit = this.getRetrofit();
        RecipesCall recipesCall = retrofit.create(RecipesCall.class);
        Call<RecipeJSON> call = recipesCall.saveRecipe(recipeJson);
        return call;
    }
}
