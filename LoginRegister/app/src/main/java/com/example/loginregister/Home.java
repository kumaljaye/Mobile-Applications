package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {


    Button localbtn, disofferbtn,profilebtn,bookbtn,eventbtn,servicesbtn,logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        disofferbtn = findViewById(R.id.disofferbtn);
        profilebtn = findViewById(R.id.profilebtn);
        bookbtn = findViewById(R.id.bookbtn);
        localbtn = findViewById(R.id.localbtn);
        eventbtn = findViewById(R.id.eventsbtn);
        servicesbtn = findViewById(R.id.servicesbtn);
        logoutbtn = findViewById(R.id.logoutBtn);



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

        eventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ViewEventsActivity.class);
                startActivity(i);
                finish();
            }
        });

        servicesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, BookServicesActivity.class);
                startActivity(i);
                finish();
            }
        });

        logoutbtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(Home.this, "Logged out successfully", Toast.LENGTH_SHORT).show();

            // Redirect to Login screen
            Intent intent = new Intent(Home.this, Login.class); // Change to your login activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear backstack
            startActivity(intent);
        });

    }
}