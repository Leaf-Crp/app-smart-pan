package com.example.app_smart_pan.recipes.personalized_recipe.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_smart_pan.R;

public class FormStepsPersonalizedRecipeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personalized_recipe_steps_form, container, false);

        return root;
    }
}
