package com.example.app_smart_pan.recipes.personalized_recipe.ui.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_smart_pan.R;
import com.example.services.beans.Step;

import java.util.ArrayList;

import javax.xml.XMLConstants;

public class StepListAdapter extends ArrayAdapter<Step> {

    private static final String TAG = "StepListAdapter";

    private Context mContext;
    private int mRessource;

    public StepListAdapter(Context context,
                           int ressource, ArrayList<Step> objects) {
        super(context, ressource, objects);
        mContext = context;
        mRessource = ressource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String label = getItem(position).getLabel();
        Integer duration = getItem(position).getDuration();

        Step step = new Step(label, duration);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRessource, parent, false);
        //return super.getView(position, convertView, parent);
        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvBirtday = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvSex = (TextView) convertView.findViewById(R.id.textView3);
        tvName.setText(label);
        tvBirtday.setText(label.toString());
        tvSex.setText(label);

        return convertView;

    }
}
