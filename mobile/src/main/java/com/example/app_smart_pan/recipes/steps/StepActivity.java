package com.example.app_smart_pan.recipes.steps;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.recipes.steps.ui.SwipeAdapter;

import com.example.services.beans.recipe.Recipe;
import org.json.JSONObject;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class StepActivity extends AppCompatActivity {

    private StompClient mStompClient;
    private String WEBSOCKET_CONNECT_URL = "ws://192.168.0.16:8090/api/websocket-endpoint/websocket";
    private String WEBSOCKET_TOPIC = "/global-message/tick";
    private ViewPager viewPager;
    private Recipe recipe;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_step);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);

        recipe = (Recipe) getIntent().getSerializableExtra("RECIPE");
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager(), 0, recipe);

        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(0);

        connectStomp();
    }

    public void connectStomp() {
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, WEBSOCKET_CONNECT_URL);
        mStompClient.connect();
        mStompClient.topic(WEBSOCKET_TOPIC).subscribe(topicMessage -> {
            JSONObject json = new JSONObject(topicMessage.getPayload());
            String result = json.getString("payload");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    recipe.getSteps().get(viewPager.getCurrentItem()).setOk(true);
                }
            });
        }, error -> {
            Log.d("TAG", error.getMessage());
        });
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

}
