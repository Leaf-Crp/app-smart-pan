package com.example.app_smart_pan.steps;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_smart_pan.R;
import com.example.services.beans.Ingredients;


import java.util.List;

public class IngredientAdapter extends BaseAdapter {

    Context context;
    List<Ingredients> ingredients;

    public  IngredientAdapter(Context context, List<Ingredients> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(context, R.layout.card_ingredient_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.ingredient_imageView);
        TextView textViewQuantity = convertView.findViewById(R.id.quantity);

        Ingredients ingredient = ingredients.get(position);

        int resourceId = context.getResources().getIdentifier(ingredient.getImage(), "drawable", context.getPackageName());
        imageView.setImageResource(resourceId);
        textViewQuantity.setText(""+ingredient.getStepIngredient().getQuantity());
        return convertView;
    }
}
