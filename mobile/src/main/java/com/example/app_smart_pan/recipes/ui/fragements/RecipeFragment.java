package com.example.app_smart_pan.recipes.ui.fragements;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.login.SessionManager;
import com.example.app_smart_pan.recipes.steps.StepActivity;
import com.example.app_smart_pan.recipes.ui.adapter.ListRecipeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.example.services.beans.recipe.Recipe;
import com.example.services.repository.RecipeRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeFragment extends Fragment {

    private TextView textViewResult;
    private ListView listView;
    private ArrayList<Recipe> recipes;
    private ListRecipeAdapter listRecipeAdapter;
    private RecipeRepository recipeRepository;
    private SessionManager sessionManager;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipes, container, false);
        sessionManager = new SessionManager(getContext());
        listView = root.findViewById(R.id.list_recipe);
        textViewResult = root.findViewById(R.id.text_view_result);
        recipeRepository = new RecipeRepository();
        HashMap<String, String> user = sessionManager.getUserDetail();
        allRecipes();

        return root;
    }



    public void allRecipes() {
        Call<List<Recipe>> call = recipeRepository.allRecipe();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipes = response.body().stream().collect(Collectors.toCollection(ArrayList::new));
                listRecipeAdapter = new ListRecipeAdapter(getContext(), recipes);
                listView.setAdapter(listRecipeAdapter);
                listView.setOnItemClickListener((parent, view, position, id) -> {
                    openStepActivity(recipes.get(position));
                });
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    public void openStepActivity(Recipe recipe) {
        Intent intent = new Intent();
        intent.setClass(getContext(), StepActivity.class);
        intent.putExtra("RECIPE", recipe);
        startActivity(intent);
    }
}
