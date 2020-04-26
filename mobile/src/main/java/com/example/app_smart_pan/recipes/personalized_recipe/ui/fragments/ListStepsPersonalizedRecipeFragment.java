package com.example.app_smart_pan.recipes.personalized_recipe.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.recipes.personalized_recipe.ListPersonalizedRecipeActivity;
import com.example.app_smart_pan.recipes.personalized_recipe.PersonalizedRecipeStepsActivity;
import com.example.app_smart_pan.recipes.personalized_recipe.ui.adapter.StepListAdapter;
import com.example.services.beans.steprecipe.Step;

import java.util.ArrayList;

public class ListStepsPersonalizedRecipeFragment extends Fragment {
    private ListView lvSteps;
    private Context context;
    private Button btnAddStep;
    private String foo;
    private TextView vstup;
    private ArrayList<Step> stepArrayList;
    private Button BtnSaveRecipe;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personalized_recipe_steps_list, container, false);
        lvSteps = root.findViewById(R.id.lvSteps);
        btnAddStep = root.findViewById(R.id.btnAddStep);
        BtnSaveRecipe = root.findViewById(R.id.BtnSaveRecipe);
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

        BtnSaveRecipe.setOnClickListener((view) -> {
            PersonalizedRecipeStepsActivity activity = (PersonalizedRecipeStepsActivity) getActivity();
             activity.sendRecipeRequest(stepArrayList);

          /*  Intent i = new Intent(activity, ListPersonalizedRecipeActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);*/
            Intent intent = new Intent();
            intent.setClass(getContext(), ListPersonalizedRecipeActivity.class);
            startActivity(intent);

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
