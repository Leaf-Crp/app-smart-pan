package com.example.app_smart_pan.recipes.personalized_recipe.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.recipes.personalized_recipe.ui.adapter.IngredientListAdapter;
import com.example.app_smart_pan.recipes.personalized_recipe.ui.adapter.PrerequisiteTypeListAdapter;
import com.example.services.beans.Ingredient.Ingredient;
import com.example.services.beans.Ingredient.Ingredients;
import com.example.services.beans.PrerequisiteType.PrerequisiteType;
import com.example.services.beans.PrerequisiteType.PrerequisiteTypes;
import com.example.services.beans.Step;
import com.example.services.repository.IngredientRepository;
import com.example.services.repository.PrerequisiteTypeRepository;

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
    private Button btnAddPrerequisite;
    private ListView lvAddIngredients;
    private ListView lvAddPrerequisite;
    private ArrayList<Step> stepArrayList;
    private IngredientRepository ingredientRepository = new IngredientRepository();
    private PrerequisiteTypeRepository prerequisiteTypeRepository= new PrerequisiteTypeRepository();
    private ArrayList<Ingredient> ingredients;
    private ArrayList<PrerequisiteType> prerequisiteTypes;

    SpinnerDialog spinnerDialogIngredients;
    SpinnerDialog spinnerDialogPrerequisiteTypes;

    private ArrayList<String> items = new ArrayList<>();
    private Context context;
    ArrayList<Ingredient> ingredientsArrayList;
    IngredientListAdapter ingredientListAdapter;
    ArrayList<PrerequisiteType> prerequisiteTypesArrayList;
    PrerequisiteTypeListAdapter prerequisiteTypeListAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_personalized_recipe_steps_form, container, false);
        btnSaveStep = root.findViewById(R.id.btnSaveStep);
        etDurationStep = root.findViewById(R.id.etDurationStep);
        etLabelStep = root.findViewById(R.id.etLabelStep);
        lvAddIngredients = root.findViewById(R.id.lvAddIngredients);
        lvAddPrerequisite = root.findViewById(R.id.lvAddPrerequisite);

        btnAddIngredient = root.findViewById(R.id.btnAddIngredient);
        btnAddPrerequisite = root.findViewById(R.id.btnAddPrerequisite);

        Bundle bundle = getArguments();
        Button btnShow;
        context = container.getContext();
        ingredientsArrayList = new ArrayList<Ingredient>();
        ingredientListAdapter = new IngredientListAdapter(context, R.layout.adapter_fragment_personalized_recipe_ingredients_list,
                ingredientsArrayList);
        lvAddIngredients.setAdapter(ingredientListAdapter);

        prerequisiteTypesArrayList = new ArrayList<PrerequisiteType>();
        prerequisiteTypeListAdapter = new PrerequisiteTypeListAdapter(context, R.layout.adapter_fragment_personalized_recipe_ingredients_list,
                prerequisiteTypesArrayList);
        lvAddPrerequisite.setAdapter(prerequisiteTypeListAdapter);

        loadFormElements();

        btnAddIngredient.setOnClickListener(view -> {
            spinnerDialogIngredients.showSpinerDialog();
        });
        btnAddPrerequisite.setOnClickListener(view -> {
            spinnerDialogPrerequisiteTypes.showSpinerDialog();
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
       // step.setIngredients(ingredients);
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
                spinnerDialogIngredients = new SpinnerDialog(getActivity(), stringIngredients, "Choisir un ingrédient");
                spinnerDialogIngredients.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String s, int i) {
                        ingredientsArrayList.add(ingredients.get(i));
                        ingredientListAdapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void onFailure(Call<Ingredients> call, Throwable t) {
                Log.d("DEBUG-MESSAGE", t.getMessage());
            }
        });

        Call<PrerequisiteTypes> callPrerequis = prerequisiteTypeRepository.allPrerequisiteTypeQuery();
        callPrerequis.enqueue(new Callback<PrerequisiteTypes>() {
            @Override
            public void onResponse(Call<PrerequisiteTypes> callPrerequis, Response<PrerequisiteTypes> response) {
                prerequisiteTypes = (ArrayList<PrerequisiteType>) response.body().getPrerequisiteTypes();
                ArrayList<String> stringPrerequisiteTypes = (ArrayList<String>) prerequisiteTypes.stream().map(PrerequisiteType::getLabel).collect(Collectors.toList());

                spinnerDialogPrerequisiteTypes = new SpinnerDialog(getActivity(), stringPrerequisiteTypes, "Choisir un prérequis");
                spinnerDialogPrerequisiteTypes.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String s, int i) {
                        prerequisiteTypesArrayList.add(prerequisiteTypes.get(i));
                        prerequisiteTypeListAdapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void onFailure(Call<PrerequisiteTypes> callPrerequis, Throwable t) {
                Log.d("DEBUG-MESSAGE", t.getMessage());
            }
        });
    }
}
