package com.example.app_smart_pan.ui.steps;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.app_smart_pan.ui.steps.fragments.FragmentStep;
import com.example.services.beans.Recipe;


public class SwipeAdapter extends FragmentStatePagerAdapter {

    private Recipe recipe;

    public SwipeAdapter(FragmentManager fm, int bh, Recipe recipe) {
        super(fm, bh);
        this.recipe =  recipe;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FragmentStep();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber", position+1);
        bundle.putSerializable("STEP", recipe.getSteps().get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return recipe.getSteps().size();
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe   (Recipe recipe) {
        this.recipe = recipe;
    }
}
