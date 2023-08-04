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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_categories);
        categories=findViewById(R.id.lstCategories);
        // Construct the data source
        ArrayList<Category> arrayOfCategory = new ArrayList<>();
        //api call
        String url = "http://10.0.2.2:3000/categories";
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
                                String categoryImage=categoryObject.getString("url");
                                arrayOfCategory.add(new Category(categoryName,categoryImage,String.valueOf(categoryId)));
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
                Category selectedCategory=(Category) parent.getItemAtPosition(position);
                String categoryId = selectedCategory.getId();
                Intent intent=new Intent(getApplicationContext(), Items.class);
                intent.putExtra("CATEGORY_ID",categoryId);
                startActivity(intent);
            }
        });
    }
}