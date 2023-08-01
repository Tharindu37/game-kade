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

import org.w3c.dom.Text;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Item> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item item=getItem(position);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
        }

        ImageView itemImage=(ImageView) convertView.findViewById(R.id.imgItem);
        TextView name=(TextView) convertView.findViewById(R.id.texItemName);
        TextView price=(TextView) convertView.findViewById(R.id.txtItemPrice);

        Glide.with(convertView).load(item.getUrl()).into(itemImage);
        name.setText(item.getName());
        price.setText(String.valueOf(item.getPrice()));

        return convertView;
    }
}
