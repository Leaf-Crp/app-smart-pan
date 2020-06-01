package com.example.services.beans.recipe;

import com.example.services.beans.message.Message;
import com.example.services.beans.steprecipe.Step;
import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private int id;
    private int nbEtape;
    private int temps;
    private int recipeTypeId = 1;
    private String label = null;
    private Boolean isPrivate = false;
    private String image;
    private List<Step> steps;
    private List<Message> messages;


    public List<Step> getSteps() {
        return steps;
    }

    public List<Message> getMessages() {
        return messages;
    }


    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public int getNbEtape() {
        return steps.size();
    }

    public int getNbMessages() {
        return messages.size();
    }
    public int getTemps() {
        int time = 0;
        for (Step step: steps) {
            time += step.getDuration();
        }
        return time;
    }

    public int getRecipeTypeId() {
        return recipeTypeId;
    }

    public void setRecipeTypeId(int recipeTypeId) {
        this.recipeTypeId = recipeTypeId;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
