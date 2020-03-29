package com.example.app_smart_pan.recipes.personalized_recipe;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_smart_pan.R;
import com.example.services.api.RecipeTypesCall;
import com.example.services.beans.Recipe;
import com.example.services.beans.RecipeType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Log.d("DEBUG", "GNEUGNEU");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.20:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeTypesCall recipeTypesCall = retrofit.create(RecipeTypesCall.class);

        Call<List<RecipeType>> call = recipeTypesCall.allRecipeTypes();

        call.enqueue(new Callback<List<RecipeType>>() {
            @Override
            public void onResponse(Call<List<RecipeType>> call, Response<List<RecipeType>> response) {

                recipeTypes = response.body().stream().collect(Collectors.toCollection(ArrayList::new));

                ArrayAdapter<RecipeType> adapter =
                        new ArrayAdapter<RecipeType>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, recipeTypes);
                adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                spinnerRecipeType.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<RecipeType>> call, Throwable t) {
                Log.d("DEBUG", t.getMessage());
            }
        });
    }
}