package com.example.services.repository;

import com.example.services.api.IngredientsCall;
import com.example.services.api.PrerequisiteTypesCall;
import com.example.services.api.config.Config;
import com.example.services.beans.Ingredient.Ingredient;
import com.example.services.beans.Ingredient.Ingredients;
import com.example.services.beans.PrerequisiteType.PrerequisiteType;
import com.example.services.beans.PrerequisiteType.PrerequisiteTypes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrerequisiteTypeRepository {
    private ArrayList<PrerequisiteType> prerequisiteTypes;

    public Call<PrerequisiteTypes> allPrerequisiteTypeQuery() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PrerequisiteTypesCall prerequisiteTypesCall = retrofit.create(PrerequisiteTypesCall.class);

        Call<PrerequisiteTypes> call = prerequisiteTypesCall.getPrerequisiteTypes();
        return call;
    }
}
