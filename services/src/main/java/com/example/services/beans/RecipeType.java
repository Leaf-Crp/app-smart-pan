package com.example.services.beans;

public class RecipeType {
    private int id;
    private String label;

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

    /**
     *
     * @return label
     */
    @Override
    public String toString() {
        return label;
    }
}
