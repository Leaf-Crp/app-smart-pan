package com.example.app_smart_pan.ui.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.StepActivity;

public class RecipesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecipesViewModel recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recipes, container, false);
        CardView cardView = root.findViewById(R.id.cardView1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStepActivity();
            }
        });
        return root;
    }

    public void openStepActivity() {
        Intent intent = new Intent();
        intent.setClass(getContext(), StepActivity.class);
        startActivity(intent);
    }
}
