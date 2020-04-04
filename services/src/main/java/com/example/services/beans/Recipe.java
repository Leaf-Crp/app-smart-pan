package com.example.services.beans;


import com.sun.org.apache.xpath.internal.operations.Bool;

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

    public Recipe(String labelRecipe, Boolean isPrivateRecipe, Integer recipeTypeId){
        label = labelRecipe;
        isPrivate = isPrivateRecipe;
        recipeTypeId = recipeTypeId;
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

    public int getNbEtape() {
        return steps.size();
    }

    public int getTemps() {
        int time = 0;
        for (Step step: steps) {
            time += step.getDuration();
        }
        return time;
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

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}