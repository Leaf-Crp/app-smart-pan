package com.example.app_smart_pan.recipes.personalized_recipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_smart_pan.R;
import com.example.services.beans.addrecipe.Recipe;
import com.example.services.beans.addrecipe.RecipeJSON;
import com.example.services.beans.steprecipe.Step;
import com.example.services.repository.RecipeRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalizedRecipeStepsActivity extends AppCompatActivity {
    private Recipe recipe;
    private RecipeRepository recipeRepository;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_recipe_steps);
        recipeRepository = new RecipeRepository();
        getSupportActionBar().hide();
        recipe = (Recipe) getIntent().getSerializableExtra("RECIPE");
    }

    public void sendRecipeRequest(ArrayList<Step> stepArrayList) {
        recipe.setSteps(stepArrayList);
        Call<RecipeJSON> call = recipeRepository.saveRecipe(recipe);
        call.enqueue(new Callback<RecipeJSON>() {
            @Override
            public void onResponse(Call<RecipeJSON> call, Response<RecipeJSON> response) {
                RecipeJSON recipeResponse = response.body();
            }

            @Override
            public void onFailure(Call<RecipeJSON> call, Throwable t) {
                Log.d("response ok", t.getMessage());
            }
        }); //finish();


      //  Intent intent = new Intent();
      //  intent.setClass(getApplicationContext(), ListPersonalizedRecipeActivity.class);
      ///  startActivity(intent);

    }
}
