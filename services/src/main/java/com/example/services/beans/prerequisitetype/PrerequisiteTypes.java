package com.example.services.beans.prerequisitetype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrerequisiteTypes {
    @SerializedName("prerequisite_types")
    @Expose
    private List<PrerequisiteType> prerequisiteTypes;

    public List<PrerequisiteType> getPrerequisiteTypes() {
        return prerequisiteTypes;
    }

    public void setPrerequisiteTypes(List prerequisiteTypes) {
        this.prerequisiteTypes = prerequisiteTypes;
    }
}
