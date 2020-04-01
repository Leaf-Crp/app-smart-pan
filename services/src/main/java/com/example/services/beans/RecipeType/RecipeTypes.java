package com.example.services.beans.RecipeType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeTypes {
    @SerializedName("recipe_types")
    @Expose
    private List<RecipeType> recipeTypes;

    public List<RecipeType> getRecipeTypes() {
        return recipeTypes;
    }

    public void setRecipeTypes(List recipesTypes) {
        this.recipeTypes = recipesTypes;
    }
}

