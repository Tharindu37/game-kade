package com.example.game_kade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Shop extends AppCompatActivity {

    private TextView time,name,address;
    private ImageView shopImage;

    private ImageButton closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shop);
        time=findViewById(R.id.txtTime);
        name=findViewById(R.id.texShopName);
        address=findViewById(R.id.txtAddress);
        shopImage=findViewById(R.id.imgShop);
        closeButton=findViewById(R.id.btnClose);

        Intent intent = getIntent();
        if (intent.hasExtra("SHOP_ID")){
            String shopId = intent.getStringExtra("SHOP_ID");
            //api call
            String url="http://10.0.2.2:3000/shops/"+shopId;
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                name.setText(response.getString("name"));
                                address.setText(response.getString("address"));
                                time.setText(response.getString("time"));
                                Glide.with(getApplicationContext()).load(response.getString("url")).into(shopImage);
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
        }else {
            Toast.makeText(this, "Category ID not found.", Toast.LENGTH_SHORT).show();
        }

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getApplicationContext(),Options.class);
                startActivity(intent1);
            }
        });
    }
}