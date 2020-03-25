package com.example.services.beans;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ingredients implements Serializable {

    private int id;
    private String label;
    private String image;
    @SerializedName("step_ingredient")
    private StepIngredient stepIngredient;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public StepIngredient getStepIngredient() {
        return stepIngredient;
    }

    public void setStepIngredient(StepIngredient stepIngredient) {
        this.stepIngredient = stepIngredient;
    }
}