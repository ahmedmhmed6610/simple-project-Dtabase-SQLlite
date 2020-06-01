package com.ahmed6610.simpleprojectdatabasesqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ahmed6610.simpleprojectdatabasesqllite.business.MainActivity;
import com.ahmed6610.simpleprojectdatabasesqllite.business.ShowData;

public class Mune extends AppCompatActivity {
Button create_date , show_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mune);

        create_date=findViewById(R.id.Cteate_data);


        show_date = findViewById(R.id.view_date);

        create_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn = new Intent(Mune.this, MainActivity.class);
                startActivity(btn);
            }
        });

        show_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn = new Intent(Mune.this, ShowData.class);
                startActivity(btn);
            }
        });
    }
}