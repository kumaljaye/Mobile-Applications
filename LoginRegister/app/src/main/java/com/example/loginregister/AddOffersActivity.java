package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class AddOffersActivity extends AppCompatActivity {

    Button add, home;
    EditText name, price, id;
    FirebaseAuth auth;
    FirebaseFirestore fs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offers);

        add = findViewById(R.id.addbtn);
        home = findViewById(R.id.homebtn);
        id = findViewById(R.id.idtxt);
        name = findViewById(R.id.nametxt);
        price = findViewById(R.id.pricetxt);

        fs = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("OfferName", name.getText().toString());

                int offerPrice = 0;
                try {
                    offerPrice = Integer.parseInt(price.getText().toString());
                } catch (NumberFormatException e) {
                    // TODO: Show error to user, or handle as needed
                    price.setError("Enter a valid number");
                    return;
                }
                hashMap.put("OfferPrice", offerPrice);

                DocumentReference docref = fs.collection("Offeres")
                        .document(id.getText().toString());
                docref.set(hashMap, SetOptions.merge());
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddOffersActivity.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }
}