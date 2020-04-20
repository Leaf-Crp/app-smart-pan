package com.example.services.beans.prerequisitetype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrerequisiteType {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("code")
    @Expose
    private String code;

    public PrerequisiteType(int id, String label, String code) {
        this.id = id;
        this.label = label;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
