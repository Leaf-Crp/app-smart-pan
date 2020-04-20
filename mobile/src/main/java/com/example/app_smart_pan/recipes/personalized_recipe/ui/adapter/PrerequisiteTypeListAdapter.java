package com.example.app_smart_pan.recipes.personalized_recipe.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_smart_pan.R;
import com.example.services.beans.prerequisitetype.PrerequisiteType;

import java.util.ArrayList;

public class PrerequisiteTypeListAdapter extends ArrayAdapter<PrerequisiteType> {
    private static final String TAG = "PrerequisiteTypeAdapter";

    private Context mContext;
    private int mRessource;

    public PrerequisiteTypeListAdapter(Context context,
                                       int ressource, ArrayList<PrerequisiteType> objects) {
        super(context, ressource, objects);
        mContext = context;
        mRessource = ressource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String label = getItem(position).getLabel();
        String code = getItem(position).getCode();

        PrerequisiteType prerequisiteType = new PrerequisiteType(1, label, code);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRessource, parent, false);
        TextView tvDetailsPrerequisite = (TextView) convertView.findViewById(R.id.textView1);

       // tvDetailsPrerequisite.setId(code);

        return convertView;
    }
    public int getQuantity(){
        return 12;
    }

}
