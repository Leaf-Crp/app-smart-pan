package com.example.app_smart_pan.ui.recipes.fragements;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.Activity.StepActivity;
import com.example.app_smart_pan.ui.recipes.adapter.ListRecipeAdapter;
import com.example.app_smart_pan.ws.WSListener;
import com.example.services.api.RecipesCall;
import com.example.services.beans.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeFragment extends Fragment {

    private TextView textViewResult;
    private ListView listView;
    private ArrayList<Recipe> recipes;
    private ListRecipeAdapter listRecipeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipes, container, false);

        listView = root.findViewById(R.id.list_recipe);
        textViewResult = root.findViewById(R.id.text_view_result);
        allRecipes();
        return root;
    }



    public void allRecipes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.29:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipesCall recipesCall = retrofit.create(RecipesCall.class);

        Call<List<Recipe>> call = recipesCall.allRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

//                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
//                    return;
//                }
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
