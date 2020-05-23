package com.example.app_smart_pan.dashboard.ui.personalized_recipe.ui.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.app_smart_pan.R;
import com.example.services.api.config.Config;
import com.example.services.beans.recipe.Recipe;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListOwnRecipesAdapter extends BaseAdapter {

    Context context;
    List<Recipe> recipes;

    public ListOwnRecipesAdapter(Context context, ArrayList<Recipe> recipes) {
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

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.card_recipe_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.recipe_imageView);
        TextView textViewLabel = convertView.findViewById(R.id.recipe_label);
        TextView textViewTemps = convertView.findViewById(R.id.recipe_temps);
        TextView textViewEtapes = convertView.findViewById(R.id.recipe_etapes);

        Recipe recipe = recipes.get(position);

        String imageUrl = Config.getUrl() + recipe.getImage();
        Picasso.get().load(imageUrl).resize(400,300).transform(new CircleTransform()).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("IMAGE", "OKOK");
            }

            @Override
            public void onError(Exception e) {
                String imageDefaultUrl = Config.getUrl() + "public/uploads/default.jpg";
                Picasso.get().load(imageDefaultUrl).resize(400,300).transform(new CircleTransform()).into(imageView);
            }
        });

        textViewLabel.setText(recipe.getLabel());
         textViewEtapes.setText(""+recipe.getNbEtape());
          textViewTemps.setText(""+recipe.getTemps());

        return convertView;
    }
}
