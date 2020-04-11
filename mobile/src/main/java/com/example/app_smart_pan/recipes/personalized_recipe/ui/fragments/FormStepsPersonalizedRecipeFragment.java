package com.example.app_smart_pan.recipes.personalized_recipe.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_smart_pan.R;
import com.example.services.beans.Step;

public class FormStepsPersonalizedRecipeFragment extends Fragment {
    private Button btnSaveStep;
    private EditText etDurationStep;
    private EditText etLabelStep;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personalized_recipe_steps_form, container, false);
        btnSaveStep = root.findViewById(R.id.btnSaveStep);
        etDurationStep = root.findViewById(R.id.etDurationStep);
        etLabelStep = root.findViewById(R.id.etLabelStep);
        btnSaveStep.setOnClickListener(view -> saveStep());
        return root;
    }

    /**
     * forme objet step et l'ajoute à la liste view
     */
    private void saveStep() {
        Step step = new Step(etLabelStep.getText().toString(), Integer.parseInt(etDurationStep.getText().toString()));
        FragmentManager frman = getFragmentManager();
        FragmentTransaction ftran = frman.beginTransaction();
        Fragment ffrag = new ListStepsPersonalizedRecipeFragment();
        Bundle args = new Bundle();
        args.putSerializable("stepToAdd", step);
        ffrag.setArguments(args);
        ftran.replace(R.id.nav_host_fragment, ffrag);
        ftran.commit();
    }
}
