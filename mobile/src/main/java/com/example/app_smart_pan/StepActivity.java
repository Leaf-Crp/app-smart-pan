package com.example.app_smart_pan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.app_smart_pan.steps.SwipeAdapter;
import com.example.services.beans.Recipe;

public class StepActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_step);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("RECIPE");
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), 0, recipe);

        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);
    }

}
