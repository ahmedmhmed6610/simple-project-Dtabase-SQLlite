package com.ahmed6610.simpleprojectdatabasesqllite.business;


import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ahmed6610.simpleprojectdatabasesqllite.DatabaseHelper;
import com.ahmed6610.simpleprojectdatabasesqllite.R;

import java.util.ArrayList;
import java.util.HashMap;


public class ShowData extends AppCompatActivity {
    private DatabaseHelper db;
    FloatingActionButton btn;
    ImageView update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        // Displaying toolbar icon
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.client);
        toolbar.setTitleTextColor(0xFFFFFFFF);



        db = new DatabaseHelper(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        final ListView lv = findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(ShowData.this, userList, R.layout.item_business, new String[]{"name", "campny", "Phone", "Email"}, new int[]{R.id.text_product_name, R.id.text_product_campny, R.id.text_product_namber, R.id.text_product_data});
        lv.setAdapter(adapter);


        btn = findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ShowData.this, MainActivity.class));
            }
        });

        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowData.this, ShowData.class));

            }
        });

    }


}