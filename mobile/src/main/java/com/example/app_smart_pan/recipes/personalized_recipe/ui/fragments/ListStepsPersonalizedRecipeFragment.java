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
    private Bundle savedState = null;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personalized_recipe_steps_list, container, false);
        lvSteps = root.findViewById(R.id.lvSteps);
        btnAddStep = root.findViewById(R.id.btnAddStep);
        ArrayList<Step> stepArrayList = new ArrayList<>();
setRetainInstance(true);
        Bundle bundle = getArguments();
        if(savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle("stav");
            Log.d("INFO","create stav");
        }
        if(savedState != null) {
            //vstup.setText(savedState.getCharSequence("vstup"));
            Log.d("INFO","create vstup");

        }
        savedState = null;
        if (bundle != null){
            Step stringArray = (Step) bundle.getSerializable("stepToAdd");
            stepArrayList.add(stringArray);
        }

        context = container.getContext();
        StepListAdapter adapter = new StepListAdapter(context, R.layout.adapter_fragment_personalized_recipe_steps_list, stepArrayList);
        lvSteps.setAdapter(adapter);

        btnAddStep.setOnClickListener((view) -> {
            FragmentManager frman = getFragmentManager();
            FragmentTransaction ftran = frman.beginTransaction();
            Fragment ffrag = new FormStepsPersonalizedRecipeFragment();
            ftran.replace(R.id.nav_host_fragment, ffrag);
            ftran.commit();
        });
        return root;
    }

/*
        Log.d("INFO","destroyed");
        vstup = null;
    }

    @SuppressLint("SetTextI18n")
    private Bundle saveState() {
        Bundle state = new Bundle();
        state.putCharSequence("vstup", "hello world");
        Log.d("INFO","saved");

        return state;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);         Log.d("INFO","saveinfostate");

        outState.putBundle("stav", (savedState != null) ? savedState : saveState());
    }



    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMyCurrentPosition = savedInstanceState.getInt("mMyCurrentPosition");
        // where mMyCurrentPosition should be a public value in your activity.
    }
*/


}
