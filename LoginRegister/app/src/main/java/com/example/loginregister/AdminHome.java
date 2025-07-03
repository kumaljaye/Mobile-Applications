package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminHome extends AppCompatActivity {

    Button addroombtn,addofferbtn,addeventbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_home);
        addroombtn = findViewById(R.id.addroombtn);
        addofferbtn = findViewById(R.id.addofferbtn);
        addeventbtn = findViewById(R.id.addeventbtn);

        addroombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHome.this, addroom.class);
                startActivity(i);
                finish();
            }
        });



        addofferbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHome.this, AddOffersActivity.class);
                startActivity(i);
                finish();
            }
        });

        addeventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHome.this, AddEvent.class);
                startActivity(i);
                finish();
            }
        });

    }

}