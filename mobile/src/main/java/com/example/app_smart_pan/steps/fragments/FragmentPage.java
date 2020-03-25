package com.example.app_smart_pan.steps.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.app_smart_pan.R;

public class FragmentPage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view;
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        String label = bundle.getString("LABEL");
        view = inflater.inflate(R.layout.fragment_step, container, false);
        TextView textView = (TextView) view.findViewById(R.id.pageNumber);
        textView.setText("ETAPE " + pageNumber + ": " + label);
        return view;
    }
}
