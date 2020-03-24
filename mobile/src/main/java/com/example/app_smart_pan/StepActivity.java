package com.example.app_smart_pan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.app_smart_pan.steps.SwipeAdapter;

public class StepActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_step);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), 0 );
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);
    }

}
