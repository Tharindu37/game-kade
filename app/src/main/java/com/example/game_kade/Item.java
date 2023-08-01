package com.example.game_kade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        if (intent.hasExtra("ITEM_ID")){
            String itemId = intent.getStringExtra("ITEM_ID");
            Toast.makeText(this, "Category ID: " + itemId, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Category ID not found.", Toast.LENGTH_SHORT).show();
        }
    }
}