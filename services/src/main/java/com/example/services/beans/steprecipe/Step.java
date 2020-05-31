package com.example.services.beans.steprecipe;

import com.example.services.beans.ingredient.Ingredient;
import com.example.services.beans.prerequisitetype.PrerequisiteType;

import java.io.Serializable;
import java.util.List;

public class Step implements Serializable {

    private int id;
    private String label;
    private int duration;
    private List<Ingredient> ingredients;
    private List<PrerequisiteType> prerequisite_type;
    private boolean isOk;

    public Step(String label, int duration, List<Ingredient> ingredients, List<PrerequisiteType> prerequisite_type) {
        this.label = label;
        this.duration = duration;
        this.ingredients = ingredients;
        this.prerequisite_type = prerequisite_type;
        this.isOk = true;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public boolean isOk() {
        return isOk;
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

    public List<PrerequisiteType> getPrerequisite_type() {
        return prerequisite_type;
    }

    public void setPrerequisite_type(List<PrerequisiteType> prerequisite_type) {
        this.prerequisite_type = prerequisite_type;
    }

}
