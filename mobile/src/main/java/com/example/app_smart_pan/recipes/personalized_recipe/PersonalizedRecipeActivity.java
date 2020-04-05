package com.example.app_smart_pan.recipes.personalized_recipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_smart_pan.R;
import com.example.services.beans.Recipe;
import com.example.services.beans.RecipeType.RecipeType;
import com.example.services.beans.RecipeType.RecipeTypes;
import com.example.services.repository.RecipeTypeRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activité de création d'une recette perso en 4 fragments avc 1 étape/fragment
 */
public class PersonalizedRecipeActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    private Switch swIsPrivateType;
    private EditText etLabelRecipe;
    private RecipeTypeRepository recipeTypeRepository;
    private Spinner spinnerRecipeType;
    private ArrayList<RecipeType> recipeTypes;
    private Button btnSave;
    private ImageView imgImageRecipe;
    private Button btnUploadImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_recipe_main);
        swIsPrivateType = (Switch) findViewById(R.id.swIsPrivateRecipe);
        spinnerRecipeType = findViewById(R.id.spTypeRecipe);
        etLabelRecipe = findViewById(R.id.etLabelRecipe);
        btnSave = findViewById(R.id.btnSave);
        imgImageRecipe = findViewById(R.id.imgImageRecipe);
        btnUploadImage = findViewById(R.id.btnUploadImage);

        btnUploadImage.setOnClickListener((view) -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        });

        getSupportActionBar().hide(); //hide the title bar
        recipeTypeRepository = new RecipeTypeRepository();
        loadFormElements();
        btnSave.setOnClickListener(view -> nextStep());
    }

    /**
     * Affiche l'image prévisionnelle quand on la chosit
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imgImageRecipe.setImageURI(selectedImage);
        }
    }


    /**
     * Charge les éléments du forum : types de recette dans la comboBox
     */
    private void loadFormElements() {
        Call<RecipeTypes> call = recipeTypeRepository.allRecipeTypesQuery();
        call.enqueue(new Callback<RecipeTypes>() {
            @Override
            public void onResponse(Call<RecipeTypes> call, Response<RecipeTypes> response) {
                recipeTypes = (ArrayList<RecipeType>) response.body().getRecipeTypes();
                ArrayAdapter<RecipeType> adapter =
                        new ArrayAdapter<RecipeType>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, recipeTypes);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRecipeType.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RecipeTypes> call, Throwable t) {
                Log.d("DEBUG-MESSAGE", t.getMessage());
            }
        });
    }

    /**
     * récupère elements et passe à l'étape de formulaire suivante => les étapes de la recette perso
     */
    private void nextStep() {
        Recipe recipe = new Recipe(etLabelRecipe.getText().toString(), swIsPrivateType.isChecked(), (int) spinnerRecipeType.getSelectedItemId());
        Intent intent = new Intent(this, PersonalizedRecipeStepsActivity.class);
        intent.putExtra("RECIPE", recipe);
        startActivity(intent);
    }
}