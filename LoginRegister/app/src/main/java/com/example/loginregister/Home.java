package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {


    Button localbtn, disofferbtn,profilebtn,bookbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        disofferbtn = findViewById(R.id.disofferbtn);
        profilebtn = findViewById(R.id.profilebtn);
        bookbtn = findViewById(R.id.bookbtn);
        localbtn = findViewById(R.id.localbtn);



        disofferbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, offersDisplayActivity.class);
                startActivity(i);
                finish();
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Profile.class);
                startActivity(i);
                finish();
            }
        });

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, BookRoom.class);
                startActivity(i);
                finish();
            }
        });

        localbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, LocalAttractions.class);
                startActivity(i);
                finish();
            }
        });

    }
}