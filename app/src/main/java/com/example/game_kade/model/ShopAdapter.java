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

public class ShopAdapter extends ArrayAdapter<Shop> {

    public ShopAdapter(Context context, ArrayList<Shop> shops) {
        super(context, 0,shops);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Shop shop=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop, parent, false);
        }

        ImageView shopImage=convertView.findViewById(R.id.imgShops);
        TextView name= convertView.findViewById(R.id.txtShops);
        Glide.with(convertView).load(shop.url).into(shopImage);
        name.setText(shop.name);
        return  convertView;
    }
}
