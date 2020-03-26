package com.example.app_smart_pan.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.ui.steps.SwipeAdapter;
import com.example.services.beans.Recipe;

import org.json.JSONObject;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class StepActivity extends AppCompatActivity {

    private StompClient mStompClient;
    private String WEBSOCKET_CONNECT_URL = "ws://192.168.1.29:8090/api/websocket-endpoint/websocket";
    private String WEBSOCKET_TOPIC = "/global-message/tick";

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
                }
            });
        }, error -> {
            Log.d("TAG", error.getMessage());
        });
    }
}
