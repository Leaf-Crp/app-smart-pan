package com.example.app_smart_pan.messages.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.messages.ui.adapter.ListMessageAdapter;
import com.example.services.beans.message.Message;
import com.example.services.beans.message.Messages;
import com.example.services.beans.recipe.Recipe;

public class MessageActivity extends AppCompatActivity {
    private Recipe recipe;
    private ListMessageAdapter messageAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_messages_list);
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        for (Message message : recipe.getMessages()){
            Log.d("stringifdf", message.getContent());
        }
        messageAdapter = (new ListMessageAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, recipe.getMessages()));




    }
}
