package com.example.game_kade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
                                    double price= itemObject.getDouble("price");
                                    String id=itemObject.getString("id");
                                    String categoryId=itemObject.getString("category");
                                    String description=itemObject.getString("description");


                                    arrayOfItems.add(new Item(id,categoryId,itemName,description,"data:image/jpeg;base64,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",price));


                                }
                                ItemAdapter adapter=new ItemAdapter(getApplicationContext(),arrayOfItems);
                                items.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("eeeeeeeeeeeeeeeeeee"+e);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("errorrrrrrrrrrrrrrrrrrr"+error);
                            // This method will be called when there is an error with the request
                            Toast.makeText(getApplicationContext(), "Error fetching categories: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            requestQueue.add(jsonArrayRequest);

            //end api call
            // Now you have the categoryId value, and you can use it as needed
            // For example, you can use it to load items related to the specific category from a database or API


            // For now, let's just show a toast message with the categoryId
            Toast.makeText(this, "Category ID: " + categoryId, Toast.LENGTH_SHORT).show();
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