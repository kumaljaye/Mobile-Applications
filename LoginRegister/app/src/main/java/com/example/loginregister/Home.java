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

public class Home extends AppCompatActivity {


    Button addroombtn, disofferbtn, addofferbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        addroombtn = findViewById(R.id.addroombtn);
        disofferbtn = findViewById(R.id.disofferbtn);
        addofferbtn = findViewById(R.id.addofferbtn);

    addroombtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Home.this, addroom.class);
            startActivity(i);
            finish();
        }
    });

        disofferbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, offersDisplayActivity.class);
                startActivity(i);
                finish();
            }
        });

        addofferbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, AddOffersActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}