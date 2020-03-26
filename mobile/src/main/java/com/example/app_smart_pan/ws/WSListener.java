package com.example.app_smart_pan.ws;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import org.java_websocket.WebSocket;



public class WSListener {


    private String WEBSOCKET_CONNECT_URL = "http://192.168.1.29:8090/api/websocket-endpoint";
    private String WEBSOCKET_TOPIC = "/global-message/tick";

    public void connectStomp(Context context) {

    }
    private void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}