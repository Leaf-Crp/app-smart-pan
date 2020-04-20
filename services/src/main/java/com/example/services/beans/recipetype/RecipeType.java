package com.example.services.beans.recipetype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeType {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("label")
    @Expose
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
