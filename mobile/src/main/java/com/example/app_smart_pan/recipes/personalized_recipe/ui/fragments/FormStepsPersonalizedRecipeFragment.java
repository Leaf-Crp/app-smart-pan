package com.example.app_smart_pan.recipes.personalized_recipe.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.recipes.personalized_recipe.ui.adapter.IngredientListAdapter;
import com.example.services.beans.Ingredient.Ingredient;
import com.example.services.beans.Ingredient.Ingredients;
import com.example.services.beans.RecipeType.RecipeType;
import com.example.services.beans.RecipeType.RecipeTypes;
import com.example.services.beans.Step;
import com.example.services.repository.IngredientRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormStepsPersonalizedRecipeFragment extends Fragment {
    private Button btnSaveStep;
    private EditText etDurationStep;
    private EditText etLabelStep;
    private Button btnAddIngredient;
    private ListView lvAddIngredients;
    private ArrayList<Step> stepArrayList;
    private IngredientRepository ingredientRepository;
    private ArrayList<Ingredient> ingredients;

    SpinnerDialog spinnerDialog;
    private ArrayList<String> items = new ArrayList<>();
    private Context context;
    ArrayList<Ingredient> ingredientsArrayList;
    IngredientListAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personalized_recipe_steps_form, container, false);
        btnSaveStep = root.findViewById(R.id.btnSaveStep);
        etDurationStep = root.findViewById(R.id.etDurationStep);
        etLabelStep = root.findViewById(R.id.etLabelStep);
        lvAddIngredients = root.findViewById(R.id.lvAddIngredients);
        btnAddIngredient = root.findViewById(R.id.btnAddIngredient);
        Bundle bundle = getArguments();
        Button btnShow;
        context = container.getContext();
        ingredientRepository = new IngredientRepository();
        ingredientsArrayList = new ArrayList<Ingredient>();
        adapter = new IngredientListAdapter(context, R.layout.adapter_fragment_personalized_recipe_ingredients_list,
                ingredientsArrayList);
        lvAddIngredients.setAdapter(adapter);
        loadFormElements();

        btnAddIngredient.setOnClickListener(view -> {
            spinnerDialog.showSpinerDialog();
        });

        if (bundle != null) {
            stepArrayList = (ArrayList<Step>) bundle.getSerializable("stepToAdd");
        }

        btnSaveStep.setOnClickListener(view -> saveStep());

        return root;
    }

    /**
     * forme objet step et l'ajoute à la liste view
     */
    private void saveStep() {
        Step step = new Step(etLabelStep.getText().toString(), Integer.parseInt(etDurationStep.getText().toString()));
        stepArrayList.add(step);
        FragmentManager frman = getFragmentManager();
        FragmentTransaction ftran = frman.beginTransaction();
        Fragment ffrag = new ListStepsPersonalizedRecipeFragment();
        Bundle args = new Bundle();
        args.putSerializable("stepToAdd", stepArrayList);
        ffrag.setArguments(args);
        ftran.replace(R.id.nav_host_fragment, ffrag);
        ftran.commit();
    }

    /**
     * Charge les éléments du form : ingredients dans la comboBox
     */
    private void loadFormElements() {
        Call<Ingredients> call = ingredientRepository.allIngredientsQuery();
        call.enqueue(new Callback<Ingredients>() {
            @Override
            public void onResponse(Call<Ingredients> call, Response<Ingredients> response) {
                ingredients = (ArrayList<Ingredient>) response.body().getIngredients();
                ArrayList<String> stringIngredients = (ArrayList<String>) ingredients.stream().map(Ingredient::getLabel).collect(Collectors.toList());

                spinnerDialog = new SpinnerDialog(getActivity(), stringIngredients, "Choisir un ingrédient");
                spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String s, int i) {
                        ingredientsArrayList.add(ingredients.get(i));
                        adapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onFailure(Call<Ingredients> call, Throwable t) {
                Log.d("DEBUG-MESSAGE", t.getMessage());
            }
        });
    }
}
