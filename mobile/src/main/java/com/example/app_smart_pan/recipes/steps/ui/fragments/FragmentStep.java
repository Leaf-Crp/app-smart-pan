package com.example.app_smart_pan.recipes.steps.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.recipes.steps.StepActivity;
import com.example.app_smart_pan.recipes.steps.ui.adapter.ListIngredientAdapter;
import com.example.services.beans.prerequisitetype.PrerequisiteType;
import com.example.services.beans.steprecipe.Step;

public class FragmentStep extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view;
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        Step step = (Step) bundle.getSerializable("STEP");
        view = inflater.inflate(R.layout.fragment_step, container, false);
        TextView textView = view.findViewById(R.id.step_label);
        textView.setText("ETAPE " + pageNumber + ": " + step.getLabel());
        ViewPager viewPager = ((StepActivity)getActivity()).getViewPager();
        PrerequisiteType prerequisiteTypes = step.getPrerequisite_type().stream()
                .filter(prerequisiteType -> "min_temperature".equals(prerequisiteType.getCode()))
                .findAny()
                .orElse(null);
        step.setOk(prerequisiteTypes == null);
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // disable swipe
                if(!step.isOk()) {
                    if (viewPager.getAdapter().getCount()>1) {
                        viewPager.setCurrentItem(pageNumber);
                        viewPager.setCurrentItem(pageNumber-1);
                    }
                }
            }
            public void onPageScrollStateChanged(int state) {}
            public void onPageSelected(int position) {}
        };
        viewPager.addOnPageChangeListener(onPageChangeListener);
        ListView listView = view.findViewById(R.id.list_ingredients);
        ListIngredientAdapter listIngredientAdapter = new ListIngredientAdapter(getContext(), step.getIngredients());
        listView.setAdapter(listIngredientAdapter);

        return view;
    }
}
