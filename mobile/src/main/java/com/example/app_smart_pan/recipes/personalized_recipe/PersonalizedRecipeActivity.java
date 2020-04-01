package com.example.app_smart_pan.recipes.personalized_recipe;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_smart_pan.R;
import com.example.services.api.RecipeTypesCall;
import com.example.services.api.config.Config;
import com.example.services.beans.RecipeType.RecipeType;
import com.example.services.beans.RecipeType.RecipeTypes;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activité de création d'une recette perso en 4 fragments avc 1 étape/fragment
 */
public class PersonalizedRecipeActivity extends AppCompatActivity {
    private Switch swIsRecipeType;
    private EditText etLabelRecipe;

    private Spinner spinnerRecipeType;
    private ArrayList<RecipeType> recipeTypes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_recipe_main);
        swIsRecipeType = (Switch) findViewById(R.id.swIsPrivateRecipe);
        spinnerRecipeType = findViewById(R.id.spTypeRecipe);
        etLabelRecipe = findViewById(R.id.etLabelRecipe);
        getSupportActionBar().hide(); //hide the title bar
        allRecipeTypes();
    }

    private void allRecipeTypes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeTypesCall recipeTypesCall = retrofit.create(RecipeTypesCall.class);

        Call<RecipeTypes> call = recipeTypesCall.getReviews();

        call.enqueue(new Callback<RecipeTypes>() {
            @Override
            public void onResponse(Call<RecipeTypes> call, Response<RecipeTypes> response) {

                recipeTypes = (ArrayList<RecipeType>) response.body().getRecipeTypes();

                ArrayAdapter<RecipeType> adapter =
                        new ArrayAdapter<RecipeType>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, recipeTypes);
                adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                spinnerRecipeType.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RecipeTypes> call, Throwable t) {
                Log.d("DEBUG-MESSAGE", t.getMessage());
            }
        });
    }
}