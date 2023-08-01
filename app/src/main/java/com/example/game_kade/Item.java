package com.example.game_kade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        if (intent.hasExtra("ITEM_ID")){
            String itemId = intent.getStringExtra("ITEM_ID");
            //api call
            String url="https://game-kade-api.up.railway.app/items/"+itemId;
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String value = response.toString();
                            Toast.makeText(getApplicationContext(), "Category ID: " + value, Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error fetching categories: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            requestQueue.add(jsonObjectRequest);

            Toast.makeText(this, "Category ID: " + itemId, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Category ID not found.", Toast.LENGTH_SHORT).show();
        }
    }
}