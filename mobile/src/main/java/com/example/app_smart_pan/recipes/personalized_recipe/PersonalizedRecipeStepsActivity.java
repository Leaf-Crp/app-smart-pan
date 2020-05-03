package com.example.app_smart_pan.recipes.personalized_recipe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.app_smart_pan.R;
import com.example.services.beans.addrecipe.Recipe;
import com.example.services.beans.steprecipe.Step;
import com.example.services.repository.RecipeRepository;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalizedRecipeStepsActivity extends AppCompatActivity {
    private Recipe recipe;
    private RecipeRepository recipeRepository;
    private static final int REQUEST = 112;
    private Context mContext = PersonalizedRecipeStepsActivity.this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_recipe_steps);
        recipeRepository = new RecipeRepository();
        getSupportActionBar().hide();
        recipe = (Recipe) getIntent().getSerializableExtra("RECIPE");
    }

    public void sendRecipeRequest(ArrayList<Step> stepArrayList) {
        recipe.setSteps(stepArrayList);
        String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!hasPermissions(mContext, PERMISSIONS)) {
            ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
        } else {
            Call<ResponseBody> call =  recipeRepository.saveRecipe(recipe);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    finish();
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
        finish();

        //  Intent intent = new Intent();
        //  intent.setClass(getApplicationContext(), ListPersonalizedRecipeActivity.class);
        ///  startActivity(intent);
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
}
