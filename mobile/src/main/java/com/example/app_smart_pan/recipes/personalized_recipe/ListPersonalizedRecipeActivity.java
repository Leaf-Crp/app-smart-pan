package com.example.app_smart_pan.recipes.personalized_recipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app_smart_pan.R;
import com.example.app_smart_pan.recipes.personalized_recipe.ui.adapter.ListOwnRecipesAdapter;
import com.example.services.beans.recipe.Recipe;
import com.example.services.beans.recipe.Recipes;
import com.example.services.repository.RecipeRepository;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPersonalizedRecipeActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Recipe> recipes;
    private ListOwnRecipesAdapter listRecipeAdapter;
    private Button btnAddPersonalizedRecipe;
    private RecipeRepository recipeRepository;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_personalized_recipe);
        getSupportActionBar().hide();
        listView = findViewById(R.id.lvListRecipe);
        btnAddPersonalizedRecipe = findViewById(R.id.btnAddPersonalizedRecipe);
        btnAddPersonalizedRecipe.setOnClickListener(view -> startActivity(new Intent(ListPersonalizedRecipeActivity.this, PersonalizedRecipeActivity.class)));
        //recupérer ses own recipes
        recipeRepository = new RecipeRepository();
getRecipes();
        //afficher ds ladzpter
    }

    private void getRecipes() {
        Call<Recipes> call = recipeRepository.ownRecipesQuery();
        call.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                recipes = (ArrayList<Recipe>) response.body().getRecipes();
                Log.d("SIZE", String.valueOf(recipes.size()));
                listRecipeAdapter = new ListOwnRecipesAdapter(getApplicationContext(), recipes);
                listView.setAdapter(listRecipeAdapter);
            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                Log.d("DEBUG-MESSAGE", t.getMessage());
            }
        });
    }

}
