package com.example.game_kade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Item extends AppCompatActivity {

    private TextView name;
    private TextView description;
    private TextView price;
    private ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        name=findViewById(R.id.texItemName);
        description=findViewById(R.id.textDescription);
        price=findViewById(R.id.txtItemPrice);
        itemImage=findViewById(R.id.imgItem);

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
                            try {
                                name.setText(response.getString("item-name"));
                                price.setText(response.getString("price"));
                                description.setText(response.getString("description"));
                                Glide.with(getApplicationContext()).load(response.getString("imageURL")).into(itemImage);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

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