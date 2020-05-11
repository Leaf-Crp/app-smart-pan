package com.example.app_smart_pan;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.services.api.RecipesCall;
import com.example.services.beans.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MainActivity extends WearableActivity {

    private ListView listView;
    private ArrayList<Recipe> recipes;
    private ListRecipeAdapter listRecipeAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_recipe);
        allRecipes();
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

                recipes = response.body().stream().collect(Collectors.toCollection(ArrayList::new));
                listRecipeAdapter = new ListRecipeAdapter(getApplicationContext(), recipes);
                listView.setAdapter(listRecipeAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openStepActivity(recipes.get(position));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }

    public void openStepActivity(Recipe recipe) {
//        Intent intent = new Intent();
//        intent.setClass(getContext(), StepActivity.class);
//        intent.putExtra("RECIPE", recipe);
//        startActivity(intent);
    }
}
