package com.example.services.api;


import com.example.services.beans.PrerequisiteType.PrerequisiteType;
import com.example.services.beans.PrerequisiteType.PrerequisiteTypes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PrerequisiteTypesCall {
    @GET("prerequisite_types")
    Call<PrerequisiteTypes> getPrerequisiteTypes();
}
