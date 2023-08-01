package com.example.game_kade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.game_kade.model.Category;
import com.example.game_kade.model.CategoryAdapter;

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
        arrayOfCategory.add(new Category("one","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBAOv6mUQnO-CL5395uIacb694bjsJ4T7C5Jy5m0A&s","2"));
        arrayOfCategory.add(new Category("two","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBAOv6mUQnO-CL5395uIacb694bjsJ4T7C5Jy5m0A&s","3"));
        arrayOfCategory.add(new Category("three","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBAOv6mUQnO-CL5395uIacb694bjsJ4T7C5Jy5m0A&s","5"));
        CategoryAdapter adapter=new CategoryAdapter(this,arrayOfCategory);
//        ArrayAdapter<String> categoriesAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,categoriesList);
        categories.setAdapter(adapter);

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