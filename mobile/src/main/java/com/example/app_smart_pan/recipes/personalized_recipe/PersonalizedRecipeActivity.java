package com.example.app_smart_pan.recipes.personalized_recipe;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_smart_pan.R;
import com.example.services.api.RecipeTypesCall;
import com.example.services.api.config.Config;
import com.example.services.beans.Recipe;
import com.example.services.beans.RecipeType.RecipeType;
import com.example.services.beans.RecipeType.RecipeTypes;
import com.example.services.repository.RecipeTypeRepository;

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
    private Switch swIsPrivateType;
    private EditText etLabelRecipe;
    private RecipeTypeRepository recipeTypeRepository;
    private Spinner spinnerRecipeType;
    private ArrayList<RecipeType> recipeTypes;
    private Button btnSave;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_recipe_main);
        swIsPrivateType = (Switch) findViewById(R.id.swIsPrivateRecipe);
        spinnerRecipeType = findViewById(R.id.spTypeRecipe);
        etLabelRecipe = findViewById(R.id.etLabelRecipe);
        btnSave = findViewById(R.id.btnSave);
        getSupportActionBar().hide(); //hide the title bar
        recipeTypeRepository = new RecipeTypeRepository();
        loadFormElements();
        btnSave.setOnClickListener(view -> nextStep());
    }

    /**
     * Charge les éléments du forum : types de recette dans la comboBox
     */
    private void loadFormElements() {
        Call<RecipeTypes> call = recipeTypeRepository.allRecipeTypesQuery();
        call.enqueue(new Callback<RecipeTypes>() {
            @Override
            public void onResponse(Call<RecipeTypes> call, Response<RecipeTypes> response) {
                recipeTypes = (ArrayList<RecipeType>) response.body().getRecipeTypes();
                ArrayAdapter<RecipeType> adapter =
                        new ArrayAdapter<RecipeType>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, recipeTypes);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRecipeType.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<RecipeTypes> call, Throwable t) {
                Log.d("DEBUG-MESSAGE", t.getMessage());
            }
        });
    }

    /**
     * récupère elements et passe à l'étape de formulaire suivante => les étapes de la recette perso
     */
    private void nextStep() {
        Recipe recipe = new Recipe(etLabelRecipe.getText().toString(), swIsPrivateType.isChecked(), (int) spinnerRecipeType.getSelectedItemId());
        Log.d("DEBUG-MESSAGE", recipe.getLabel());
        //https://www.youtube.com/watch?v=e8x-nu9-_BM
    }
}