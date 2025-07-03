package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class AdminHome extends AppCompatActivity {

    Button addroombtn,addofferbtn,addeventbtn,logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_home);
        addroombtn = findViewById(R.id.addroombtn);
        addofferbtn = findViewById(R.id.addofferbtn);
        addeventbtn = findViewById(R.id.addeventbtn);
        logoutBtn = findViewById(R.id.logoutBtn);

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

        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(AdminHome.this, "Logged out successfully", Toast.LENGTH_SHORT).show();

            // Redirect to Login screen
            Intent intent = new Intent(AdminHome.this, Login.class); // Change to your login activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear backstack
            startActivity(intent);
        });

    }

}