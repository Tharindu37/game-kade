package com.example.game_kade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.game_kade.model.Category;
import com.example.game_kade.model.CategoryAdapter;
import com.example.game_kade.model.Shop;
import com.example.game_kade.model.ShopAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Shops extends AppCompatActivity {

    private ListView shops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        shops=findViewById(R.id.lstShopts);

        ArrayList<Shop> arrayOfShops = new ArrayList<>();
        String url = "http://10.0.2.2:3000/shops";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // This method will be called when the request is successful and you have received the JSON array response
                        try {
                            // Iterate through the JSON array
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject categoryObject = response.getJSONObject(i);
                                // Retrieve the values from each category object
                                String shopId = categoryObject.getString("id");
                                String shopName = categoryObject.getString("name");
                                String shopImage=categoryObject.getString("url");
                                String shopAddress=categoryObject.getString("address");
                                String time=categoryObject.getString("time");
                                System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+shopName);
                                System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu"+shopImage);
                                arrayOfShops.add(new Shop(shopId,shopName,shopAddress,time,shopImage));
                            }
                            ShopAdapter adapter=new ShopAdapter(getApplicationContext(),arrayOfShops);
                            shops.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // This method will be called when there is an error with the request
                        Toast.makeText(getApplicationContext(), "Error fetching categories: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);

        shops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shop selectedShop=(Shop) parent.getItemAtPosition(position);
                String shopId = selectedShop.getId();
                Intent intent=new Intent(getApplicationContext(), com.example.game_kade.Shop.class);
                intent.putExtra("SHOP_ID",shopId);
                startActivity(intent);
            }
        });
    }
}