package com.example.app_smart_pan.dashboard.ui.personalized_recipe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.app_smart_pan.R;
import com.example.services.beans.addrecipe.Recipe;
import com.example.services.beans.recipetype.RecipeType;
import com.example.services.beans.recipetype.RecipeTypes;
import com.example.services.repository.RecipeTypeRepository;

import java.io.File;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activité de création d'une recette perso en 4 fragments avc 1 étape/fragment
 */
public class PersonalizedRecipeActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int REQUEST = 112;
    private Switch swIsPrivateType;
    private EditText etLabelRecipe;
    private RecipeTypeRepository recipeTypeRepository;
    private Spinner spinnerRecipeType;
    private ArrayList<RecipeType> recipeTypes;
    private Button btnSave;
    private ImageView imgImageRecipe;
    private Button btnUploadImage;
    private File file;
    private Context mContext = PersonalizedRecipeActivity.this;

    @SuppressLint("IntentReset")
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

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
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
            if (resultCode == RESULT_OK) {
                Log.d("present", "ici");

                String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE};
                if (!hasPermissions(mContext, PERMISSIONS)) {
                    ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
                } else {
                    Uri selectedImage = data.getData();
                    file = new File(this.getPath(selectedImage));
                    imgImageRecipe.setImageURI(selectedImage);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("app not alloxew", "ALLLLLLLLLLLLLLLLLLLLOWEDD");

                } else {
                    Log.d("app not alloxew", "app not alloxew");
                }
            }
        }
    }


    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
        if (TextUtils.isEmpty(etLabelRecipe.getText().toString()) || imgImageRecipe.getDrawable() == null) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this, R.style.AlertDialogFormStyle);
            dlgAlert.setMessage("Vous devez remplir tous les champs.");
            dlgAlert.setTitle("Champs invalide");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
        } else {
            Recipe recipe = new Recipe(etLabelRecipe.getText().toString(), swIsPrivateType.isChecked(), (int) spinnerRecipeType.getSelectedItemId(), file);
            Intent intent = new Intent(this, PersonalizedRecipeStepsActivity.class);
            intent.putExtra("RECIPE", recipe);
            startActivity(intent);
        }
    }
}