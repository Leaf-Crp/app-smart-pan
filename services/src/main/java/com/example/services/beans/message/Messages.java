package com.example.services.beans.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Messages {
    @SerializedName("messages")
    @Expose
    private List<Message> messages;

    public List<Message> getRecipes() {
        return messages;
    }

    public void setMessages(List messages) {
        messages = messages;
    }
}
