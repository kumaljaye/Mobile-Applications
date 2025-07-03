package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddEvent extends AppCompatActivity {

    EditText etEventTitle, etEventDate, etEventLocation, etEventDescription;
    Button btnSubmitEvent,homebtn;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        etEventTitle = findViewById(R.id.etEventTitle);
        etEventDate = findViewById(R.id.etEventDate);
        etEventLocation = findViewById(R.id.etEventLocation);
        etEventDescription = findViewById(R.id.etEventDescription);
        btnSubmitEvent = findViewById(R.id.btnSubmitEvent);
        homebtn = findViewById(R.id.homebtn);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        homebtn.setOnClickListener(v -> {
            Intent i = new Intent(AddEvent.this, AdminHome.class);
            startActivity(i);
            finish();
        });

        btnSubmitEvent.setOnClickListener(v -> {
            String title = etEventTitle.getText().toString().trim();
            String date = etEventDate.getText().toString().trim();
            String location = etEventLocation.getText().toString().trim();
            String description = etEventDescription.getText().toString().trim();

            if (title.isEmpty()) {
                etEventTitle.setError("Title is required");
                return;
            }
            if (date.isEmpty()) {
                etEventDate.setError("Date is required");
                return;
            }
            if (location.isEmpty()) {
                etEventLocation.setError("Location is required");
                return;
            }
            if (description.isEmpty()) {
                etEventDescription.setError("Description is required");
                return;
            }

            // Create event map
            HashMap<String, Object> eventData = new HashMap<>();
            eventData.put("Title", title);
            eventData.put("Date", date);
            eventData.put("Location", location);
            eventData.put("Description", description);

            // Save to Firestore
            firestore.collection("Events")
                    .add(eventData)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(AddEvent.this, "Event added successfully!", Toast.LENGTH_SHORT).show();
                        clearFields();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(AddEvent.this, "Failed to add event: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }

    private void clearFields() {
        etEventTitle.setText("");
        etEventDate.setText("");
        etEventLocation.setText("");
        etEventDescription.setText("");
    }
}
