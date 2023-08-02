package com.example.game_kade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.game_kade.model.Item;
import com.example.game_kade.model.ItemAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Items extends AppCompatActivity {
    private ListView items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_items);
        items=findViewById(R.id.lstItems);
        // Get the Intent that started this activity
        Intent intent = getIntent();
        // Check if the Intent contains the "CATEGORY_ID" extra
        if (intent.hasExtra("CATEGORY_ID")) {
            ArrayList<Item> arrayOfItems=new ArrayList<>();
            // Retrieve the value of "CATEGORY_ID" from the Intent
            String categoryId = intent.getStringExtra("CATEGORY_ID");
            //api call
            String url = "http://10.0.2.2:3000/categories/"+categoryId+"/items";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            // This method will be called when the request is successful and you have received the JSON array response
                            try {
                                // Iterate through the JSON array
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject itemObject = response.getJSONObject(i);
                                    String itemName = itemObject.getString("item-name");
                                    String imageUrl = itemObject.getString("imageURL");
                                    String price= itemObject.getString("price");
                                    String id=itemObject.getString("id");
                                    String categoryId=itemObject.getString("category");
                                    String description=itemObject.getString("description");
                                    arrayOfItems.add(new Item(id,categoryId,itemName,description,imageUrl,price));
                                }
                                ItemAdapter adapter=new ItemAdapter(getApplicationContext(),arrayOfItems);
                                items.setAdapter(adapter);
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
            //end api call
            items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Item selectedItem=(Item) parent.getItemAtPosition(position);
                    String itemId=selectedItem.getId();
                    Intent intent1=new Intent(getApplicationContext(), com.example.game_kade.Item.class);
                    intent1.putExtra("ITEM_ID",itemId);
                    startActivity(intent1);
                }
            });
        } else {
            // If the Intent doesn't contain the "CATEGORY_ID" extra or the extra value is not an integer, handle the situation accordingly
            Toast.makeText(this, "Category ID not found.", Toast.LENGTH_SHORT).show();
        }
    }
}