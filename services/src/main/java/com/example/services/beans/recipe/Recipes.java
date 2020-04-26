package com.example.services.beans.recipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipes {
    @SerializedName("recipes")
    @Expose
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setPrerequisiteTypes(List recipes) {
        recipes = recipes;
    }
}
