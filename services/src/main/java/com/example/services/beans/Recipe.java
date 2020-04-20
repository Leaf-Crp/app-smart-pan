package com.example.services.beans;


import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    private int id;
    private int nbEtape;
    private int temps;
    private String label;
    private String image;
    private List<Step> steps;

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

}