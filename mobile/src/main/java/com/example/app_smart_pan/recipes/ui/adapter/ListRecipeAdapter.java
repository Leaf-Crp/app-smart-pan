package com.example.app_smart_pan.recipes.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.dashboard.ui.personalized_recipe.ShoppingListActivity;
import com.example.app_smart_pan.messages.ui.MessageActivity;
import com.example.app_smart_pan.recipes.steps.StepActivity;
import com.example.app_smart_pan.recipes.ui.fragements.RecipeFragment;
import com.example.services.beans.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ListRecipeAdapter extends BaseAdapter {

    Context context;
    List<Recipe> recipes;

    public ListRecipeAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(context, R.layout.card_recipe_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.recipe_imageView);
        TextView textViewLabel = convertView.findViewById(R.id.recipe_label);
        TextView textViewTemps = convertView.findViewById(R.id.recipe_temps);
        TextView textViewEtapes = convertView.findViewById(R.id.recipe_etapes);
        TextView tvMessages = convertView.findViewById(R.id.tvMessages);
        LinearLayout llComments = convertView.findViewById(R.id.llComments);

        Recipe recipe = recipes.get(position);

        int resourceId = context.getResources().getIdentifier(recipe.getImage(), "drawable", context.getPackageName());
        imageView.setImageResource(resourceId);
        textViewLabel.setText(recipe.getLabel());
        textViewEtapes.setText(""+recipe.getNbEtape());
        textViewTemps.setText(""+recipe.getTemps());
        tvMessages.setText(""+recipe.getNbMessages() + " messages");

        llComments.setOnClickListener((view) -> {
           Intent intent = new Intent(context, MessageActivity.class);
           context.startActivity(intent);
        });

        return convertView;
    }
}
