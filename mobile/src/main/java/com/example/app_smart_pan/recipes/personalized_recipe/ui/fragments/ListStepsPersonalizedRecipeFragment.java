package com.example.app_smart_pan.recipes.personalized_recipe.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.recipes.personalized_recipe.ui.adapter.StepListAdapter;
import com.example.services.beans.Step;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class ListStepsPersonalizedRecipeFragment extends Fragment {
    private ListView lvSteps;
    private Context context;
    private Button btnAddStep;
    private String foo;
    private TextView vstup;
    private ArrayList<Step> stepArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personalized_recipe_steps_list, container, false);
        lvSteps = root.findViewById(R.id.lvSteps);
        btnAddStep = root.findViewById(R.id.btnAddStep);
        setRetainInstance(true);
        Bundle bundle = getArguments();
        stepArrayList = new ArrayList<>();
        if (bundle != null) {
            stepArrayList = (ArrayList<Step>) bundle.getSerializable("stepToAdd");
        }

        context = container.getContext();
        StepListAdapter adapter = new StepListAdapter(context, R.layout.adapter_fragment_personalized_recipe_steps_list, stepArrayList);
        lvSteps.setAdapter(adapter);

        btnAddStep.setOnClickListener((view) -> {
            makeForm();
        });
        return root;
    }

    /**
     * forme objet step et l'ajoute à la liste view
     */
    private void makeForm() {
        FragmentManager frman = getFragmentManager();
        FragmentTransaction ftran = frman.beginTransaction();
        Fragment ffrag = new FormStepsPersonalizedRecipeFragment();
        Bundle args = new Bundle();
        args.putSerializable("stepToAdd", stepArrayList);
        ffrag.setArguments(args);
        ftran.replace(R.id.nav_host_fragment, ffrag);
        ftran.commit();
    }
}
