package com.example.app_smart_pan.messages.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.messages.ui.adapter.ListMessageAdapter;
import com.example.services.beans.message.Message;
import com.example.services.beans.message.Messages;
import com.example.services.beans.recipe.Recipe;
import com.example.services.repository.MessageRepository;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity {
    private Recipe recipe;
    private ListMessageAdapter messageAdapter;
    private RecyclerView mMessageRecycler;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button sendedButton;
    private EditText etChatbox;
    private List<Message> messages;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_messages_list);
        recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        etChatbox = findViewById(R.id.edittext_chatbox);
        sendedButton = findViewById(R.id.button_chatbox_send);
        mMessageRecycler = findViewById(R.id.reyclerview_message_list);
        mMessageRecycler.setHasFixedSize(true);
        messages = recipe.getMessages();
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mMessageRecycler.setLayoutManager(layoutManager);
        mMessageRecycler.setItemViewCacheSize(0);
        // specify an adapter (see also next example)
        mAdapter = new ListMessageAdapter(this, messages);
        mMessageRecycler.setAdapter(mAdapter);
        sendedButton.setOnClickListener(view -> send());
    }

    /**
     * Enregistre message API et affiche nouveau
     */
    public void send(){
        Integer sessionId = 1;
        Message message = new Message(etChatbox.getText().toString(), recipe.getId(), sessionId);
        MessageRepository messageRepository = new MessageRepository();
        Call<String> call =  messageRepository.create(message);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                messages.add(message);
                messageAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
