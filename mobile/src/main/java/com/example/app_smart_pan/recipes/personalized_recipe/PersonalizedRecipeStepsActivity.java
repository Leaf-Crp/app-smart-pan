package com.example.app_smart_pan.recipes.personalized_recipe;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app_smart_pan.R;
import com.example.services.beans.Recipe;

public class PersonalizedRecipeStepsActivity extends AppCompatActivity {
    private Recipe recipe;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_recipe_steps);
        getSupportActionBar().hide();
        recipe = (Recipe) getIntent().getSerializableExtra("RECIPE");
    }
}
