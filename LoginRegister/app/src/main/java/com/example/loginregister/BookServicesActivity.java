package com.example.loginregister;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookServicesActivity extends AppCompatActivity {

    private Spinner serviceSpinner;
    private EditText nameEditText, dateEditText, peopleEditText;
    private Button bookServiceButton, homeButton;

    private FirebaseFirestore firestore;
    private String[] services = {"Spa Treatment", "Fine Dining", "Poolside Cabana", "Guided Beach Tour"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // Initialize views
        serviceSpinner = findViewById(R.id.service_spinner);
        nameEditText = findViewById(R.id.nameEditText);
        dateEditText = findViewById(R.id.dateEditText);
        peopleEditText = findViewById(R.id.peopleEditText);
        bookServiceButton = findViewById(R.id.bookServiceButton);
        homeButton = findViewById(R.id.homebtn);

        // Setup spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, services);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceSpinner.setAdapter(adapter);

        // Firebase instance
        firestore = FirebaseFirestore.getInstance();

        // Book Service button logic
        bookServiceButton.setOnClickListener(v -> {
            String selectedService = serviceSpinner.getSelectedItem().toString();
            String name = nameEditText.getText().toString().trim();
            String date = dateEditText.getText().toString().trim();
            String people = peopleEditText.getText().toString().trim();

            if (name.isEmpty() || date.isEmpty() || people.isEmpty()) {
                Toast.makeText(BookServicesActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> booking = new HashMap<>();
            booking.put("service", selectedService);
            booking.put("name", name);
            booking.put("date", date);
            booking.put("peopleOrDuration", people);

            firestore.collection("ServiceBookings")
                    .add(booking)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(BookServicesActivity.this, "Service booked successfully!", Toast.LENGTH_SHORT).show();
                        nameEditText.setText("");
                        dateEditText.setText("");
                        peopleEditText.setText("");
                        serviceSpinner.setSelection(0);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(BookServicesActivity.this, "Failed to book service", Toast.LENGTH_SHORT).show();
                    });
        });

        // Home button logic
        homeButton.setOnClickListener(v -> {
            // Change this intent based on your actual home activity
            Intent intent = new Intent(BookServicesActivity.this, Home.class);
            startActivity(intent);
            finish();
        });
    }
}
