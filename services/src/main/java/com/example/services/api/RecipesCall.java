package com.example.services.api;

import com.example.services.beans.recipe.Recipe;
import com.example.services.beans.recipe.Recipes;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RecipesCall {

    @GET("recipes")
    Call<List<Recipe>> allRecipes();

    @GET("recipes/users/{id}")
    Call<Recipes> ownRecipes(@Path("id") Integer id);

    @Multipart
    @POST("/recipes")
    Call<ResponseBody> saveRecipe(@Part MultipartBody.Part requestBodyFile, @Part("recipe") RequestBody recipe);
}


