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
import com.example.app_smart_pan.R;
import com.example.app_smart_pan.recipes.steps.ui.adapter.ListIngredientAdapter;
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

        ListView listView = view.findViewById(R.id.list_ingredients);
        ListIngredientAdapter listIngredientAdapter = new ListIngredientAdapter(getContext(), step.getIngredients());
        listView.setAdapter(listIngredientAdapter);

        return view;
    }
}
