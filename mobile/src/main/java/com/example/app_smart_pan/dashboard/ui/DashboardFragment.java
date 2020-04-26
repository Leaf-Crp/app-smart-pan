package com.example.app_smart_pan.dashboard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.recipes.personalized_recipe.ListPersonalizedRecipeActivity;
import com.example.app_smart_pan.recipes.personalized_recipe.PersonalizedRecipeActivity;


public class DashboardFragment extends Fragment {

    private Button btnListOwnRecipes;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        btnListOwnRecipes = root.findViewById(R.id.btnListPersonalizedRecipesActivity);
        btnListOwnRecipes.setOnClickListener(view -> openRecipeCreationActivity());

        return root;
    }

    private void openRecipeCreationActivity() {
        Intent intent = new Intent();
        intent.setClass(getContext(), ListPersonalizedRecipeActivity.class);
        startActivity(intent);
    }
}
