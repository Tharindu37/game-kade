package com.example.game_kade.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.game_kade.R;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {
    public CategoryAdapter(Context context, ArrayList<Category> category) {
        super(context, 0, category);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get the data item for this postion
        Category category=getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categories, parent, false);
        }
        // Lookup view for data population
        ImageView categoryImage = (ImageView)  convertView.findViewById(R.id.imgCategories);
        TextView name = (TextView) convertView.findViewById(R.id.txtCategories);

        Glide.with(convertView).load(category.url).into(categoryImage);
        // Populate the data into the template view using the data object
        name.setText(category.name);
//        url.setText(category.url);
        // Return the completed view to render on screen
        return convertView;
    }
}
