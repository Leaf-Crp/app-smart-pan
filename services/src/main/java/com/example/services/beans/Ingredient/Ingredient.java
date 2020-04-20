package com.example.services.beans.ingredient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("image")
    @Expose
    private String image;

    private int quantity;

    private StepIngredient stepIngredient;

    public Ingredient(int id, String label) {
        this.id = id;
        this.label = label;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStepIngredient(StepIngredient stepIngredient) {
        this.stepIngredient = stepIngredient;
    }

    /**
     *
     * @return label
     */
    @Override
    public String toString() {
        return label;
    }
}
