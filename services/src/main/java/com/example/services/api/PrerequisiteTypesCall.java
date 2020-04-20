package com.example.services.api;


import com.example.services.beans.prerequisitetype.PrerequisiteTypes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PrerequisiteTypesCall {
    @GET("prerequisite_types")
    Call<PrerequisiteTypes> getPrerequisiteTypes();
}
