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
        Category category=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categories, parent, false);
        }

        ImageView categoryImage = (ImageView)  convertView.findViewById(R.id.imgCategories);
        TextView name = (TextView) convertView.findViewById(R.id.txtCategories);

        Glide.with(convertView).load(category.url).into(categoryImage);
        name.setText(category.name);
        return convertView;
    }
}
