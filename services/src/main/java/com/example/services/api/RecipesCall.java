package com.example.services.api;

import com.example.services.beans.addrecipe.RecipeJSON;
import com.example.services.beans.Recipe;
import com.example.services.beans.recipe.Recipes;
import com.example.services.beans.steprecipe.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RecipesCall {

    @GET("recipes")
    Call<List<Recipe>> allRecipes();

    @GET("recipes/users")
    Call<Recipes> ownRecipes();

    @Multipart
    @POST("/recipes")
    Call<ResponseBody> saveRecipe(@Part MultipartBody.Part requestBodyFile, @Part("recipe") RequestBody recipe);
}


