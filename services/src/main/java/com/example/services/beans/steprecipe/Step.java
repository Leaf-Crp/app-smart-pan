package com.example.services.beans.steprecipe;

import com.example.services.beans.ingredient.Ingredient;
import com.example.services.beans.prerequisitetype.PrerequisiteType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Step {
    private int id;
    private String label;
    private int duration;
    private List<Ingredient> ingredients;
    private List<PrerequisiteType> prerequisiteTypes;

    public Step(String label, int duration, List<Ingredient> ingredients, List<PrerequisiteType> prerequisiteTypes) {
        this.label = label;
        this.duration = duration;
        this.ingredients = ingredients;
        this.prerequisiteTypes = prerequisiteTypes;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<PrerequisiteType> getPrerequisiteTypes() {
        return prerequisiteTypes;
    }

    public void setPrerequisiteTypes(List<PrerequisiteType> prerequisiteTypes) {
        this.prerequisiteTypes = prerequisiteTypes;
    }

}
