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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.game_kade.model.Category;
import com.example.game_kade.model.CategoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {

    private ListView categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        categories=findViewById(R.id.lstCategories);

        // Construct the data source
        ArrayList<Category> arrayOfCategory = new ArrayList<>();

        //api call
        String url = "https://game-kade-api.up.railway.app/categories";
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
                                int categoryId = categoryObject.getInt("id");
                                String categoryName = categoryObject.getString("name");
                                // ... Process the data here as per your requirement
//                                System.out.println("nameeeeeeeeeeeeeeeee"+categoryName);
                                arrayOfCategory.add(new Category(categoryName,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBAOv6mUQnO-CL5395uIacb694bjsJ4T7C5Jy5m0A&s",String.valueOf(categoryId)));
                            }
                            CategoryAdapter adapter=new CategoryAdapter(getApplicationContext(),arrayOfCategory);
                            categories.setAdapter(adapter);
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
        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the selected category from the adapter
                Category selectedCategory=(Category) parent.getItemAtPosition(position);
                // Now you can access the details of the selected category (e.g., name and URL)
                String categoryId = selectedCategory.getId();

                // Perform actions based on the selected category
                // For example, you might want to display more details about the category or navigate to another activity
                // You can use an Intent to pass data to another activity if needed
                // Example: startActivity(new Intent(Categories.this, CategoryDetailsActivity.class).putExtra("category_name", categoryName));

                // For now, let's just show a toast message with the category name
//                Toast.makeText(Categories.this, "Clicked: " + categoryId, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), Items.class);
                intent.putExtra("CATEGORY_ID",categoryId);
                startActivity(intent);
            }
        });
    }
}