package com.example.services.repository;

import com.example.services.api.RecipeTypesCall;
import com.example.services.api.RecipesCall;
import com.example.services.api.config.Config;
import com.example.services.beans.addrecipe.Recipe;
import com.example.services.beans.addrecipe.RecipeJSON;
import com.example.services.beans.recipe.Recipes;
import com.example.services.beans.recipetype.RecipeTypes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRepository extends BaseRepository {

    public Call<RecipeJSON> saveRecipe(Recipe recipe) {

        RecipeJSON recipeJson = new RecipeJSON(recipe.getLabel(), recipe.getPrivate(), "you",
                1,recipe.getRecipeTypeId(), recipe.getSteps());
        Retrofit retrofit = this.getRetrofit();
        RecipesCall recipesCall = retrofit.create(RecipesCall.class);
        Call<RecipeJSON> call = recipesCall.saveRecipe(recipeJson);
        return call;
    }

    public Call<Recipes> ownRecipesQuery() {
        Retrofit retrofit = this.getRetrofit();

        RecipesCall recipesCall = retrofit.create(RecipesCall.class);

        Call<Recipes> call = recipesCall.ownRecipes();

        return call;
    }
}
