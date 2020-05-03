package com.example.services.beans.addrecipe;


import com.example.services.beans.steprecipe.Step;

import java.io.File;
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
    private File file;

    public Recipe(String labelRecipe, Boolean isPrivateRecipe, Integer recipeTypeId, File fileRecipe){
        label = labelRecipe;
        isPrivate = isPrivateRecipe;
        recipeTypeId = recipeTypeId;
        file = fileRecipe;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Step> getSteps() {
        return steps;
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
