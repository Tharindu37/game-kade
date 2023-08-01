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
//        arrayOfCategory.add(new Category("one","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBAOv6mUQnO-CL5395uIacb694bjsJ4T7C5Jy5m0A&s","2"));
//        arrayOfCategory.add(new Category("two","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBAOv6mUQnO-CL5395uIacb694bjsJ4T7C5Jy5m0A&s","3"));
//        arrayOfCategory.add(new Category("three","data:image/jpeg;base64,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","5"));
//
//        CategoryAdapter adapter=new CategoryAdapter(this,arrayOfCategory);
//        ArrayAdapter<String> categoriesAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,categoriesList);
//        categories.setAdapter(adapter);

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