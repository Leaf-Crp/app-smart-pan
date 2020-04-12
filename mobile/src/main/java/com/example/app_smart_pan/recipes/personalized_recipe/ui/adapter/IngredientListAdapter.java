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
import com.example.services.beans.Ingredient.Ingredient;
import com.example.services.beans.Step;

import java.util.ArrayList;

public class IngredientListAdapter extends ArrayAdapter<Ingredient> {
    private static final String TAG = "StepListAdapter";

    private Context mContext;
    private int mRessource;

    public IngredientListAdapter(Context context,
                           int ressource, ArrayList<Ingredient> objects) {
        super(context, ressource, objects);
        mContext = context;
        mRessource = ressource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String label = getItem(position).getLabel();

        Ingredient ingredient = new Ingredient(1, label);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRessource, parent, false);
        //return super.getView(position, convertView, parent);
        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        tvName.setText(label);

        return convertView;

    }
}
