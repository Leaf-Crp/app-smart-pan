package com.example.app_smart_pan;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;


public class MainActivity extends AppCompatActivity {

    private StompClient mStompClient;
    private String WEBSOCKET_CONNECT_URL = "http://192.168.1.29:8090/api/websocket-endpoint";
    private String WEBSOCKET_TOPIC = "/global-message/tick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_recipes, R.id.navigation_dashboard, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.1.29:8090/api/websocket-endpoint/websocket");
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
