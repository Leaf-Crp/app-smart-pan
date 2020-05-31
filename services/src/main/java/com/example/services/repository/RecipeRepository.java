package com.example.services.repository;

import com.example.services.api.RecipesCall;
import com.example.services.api.config.Config;
import com.example.services.beans.addrecipe.Recipe;
import com.example.services.beans.addrecipe.RecipeJSON;
import com.example.services.beans.recipe.Recipes;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class RecipeRepository extends BaseRepository {

    public Call<ResponseBody> saveRecipe(Recipe recipe) {

        RecipeJSON recipeJson = new RecipeJSON(recipe.getLabel(), recipe.getPrivate(), "",
                1,recipe.getRecipeTypeId(), recipe.getSteps());
        Gson gson = new Gson();
        String json = gson.toJson(recipeJson);

        RequestBody requestBody2 = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                json
        );
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), recipe.getFile());
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", recipe.getFile().getName(), requestFile);

        Retrofit retrofit = this.getRetrofit();

        RecipesCall retrofit1 = retrofit.create(RecipesCall.class);
        Call<ResponseBody> call = retrofit1.saveRecipe(multipartBody, requestBody2);
        return call;
    }

    public Call<Recipes> ownRecipesQuery() {
        Retrofit retrofit = this.getRetrofit();
        RecipesCall recipesCall = retrofit.create(RecipesCall.class);
        Call<Recipes> call = recipesCall.ownRecipes();
        return call;
    }

    public Call<List<com.example.services.beans.recipe.Recipe>> allRecipe(){
        RecipesCall recipesCall = Config.getRetrofit().create(RecipesCall.class);
        return recipesCall.allRecipes();
    }

}
