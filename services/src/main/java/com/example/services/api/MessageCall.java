package com.example.services.api;

import com.example.services.beans.message.Message;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageCall {
    @POST("/messages")
    Call<String> create(@Body Message message);

}
