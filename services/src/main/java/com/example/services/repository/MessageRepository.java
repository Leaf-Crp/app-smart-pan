package com.example.services.repository;

import com.example.services.api.MessageCall;
import com.example.services.beans.message.Message;

import retrofit2.Call;
import retrofit2.Retrofit;

public class MessageRepository extends BaseRepository
{
    public Call<String> create(Message message) {
        Retrofit retrofit = this.getRetrofit();
        MessageCall userCall = retrofit.create(MessageCall.class);
        Call<String> call = userCall.create(message);
        return call;
    }
}
