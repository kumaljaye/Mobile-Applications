package com.example.loginregister;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class BookRoom extends AppCompatActivity {

    Spinner roomSpinner;
    EditText nameEditText, dateEditText, daysEditText;
    TextView roomTypeText, bedsText, coolText, priceText, roomDetailsLabel;
    Button bookRoomButton, homebtn;
    ImageView roomImageView;

    FirebaseFirestore firestore;

    ArrayList<String> roomList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);

        roomSpinner = findViewById(R.id.room_spinner);
        nameEditText = findViewById(R.id.nameEditText);
        dateEditText = findViewById(R.id.dateEditText);
        daysEditText = findViewById(R.id.daysEditText);
        roomTypeText = findViewById(R.id.roomTypeText);
        bedsText = findViewById(R.id.bedsText);
        coolText = findViewById(R.id.coolText);
        priceText = findViewById(R.id.priceText);
        roomDetailsLabel = findViewById(R.id.roomDetailsLabel);
        roomImageView = findViewById(R.id.roomImageView);

        bookRoomButton = findViewById(R.id.bookRoomButton);
        homebtn = findViewById(R.id.homebtn);

        firestore = FirebaseFirestore.getInstance();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(adapter);

        fetchAvailableRooms();

        bookRoomButton.setOnClickListener(view -> {
            String selectedRoom = roomSpinner.getSelectedItem().toString();
            String name = nameEditText.getText().toString().trim();
            String date = dateEditText.getText().toString().trim();
            String days = daysEditText.getText().toString().trim();

            if (name.isEmpty() || date.isEmpty() || days.isEmpty()) {
                Toast.makeText(BookRoom.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            HashMap<String, Object> booking = new HashMap<>();
            booking.put("Name", name);
            booking.put("RoomNo", selectedRoom);
            booking.put("Date", date);
            booking.put("Days", days);

            firestore.collection("Bookings")
                    .add(booking)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(BookRoom.this, "Room booked successfully!", Toast.LENGTH_SHORT).show();
                        nameEditText.setText("");
                        dateEditText.setText("");
                        daysEditText.setText("");
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(BookRoom.this, "Booking failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        homebtn.setOnClickListener(v -> {
            Intent i = new Intent(BookRoom.this, Home.class);
            startActivity(i);
            finish();
        });

        roomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedRoomNo = roomList.get(i);
                showRoomDetails(selectedRoomNo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }

    private void fetchAvailableRooms() {
        firestore.collection("Rooms").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        roomList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            roomList.add(document.getString("No"));
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(BookRoom.this, "Error fetching rooms", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showRoomDetails(String roomNo) {
        firestore.collection("Rooms").document(roomNo)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String type = documentSnapshot.getString("Type");
                        String beds = documentSnapshot.getString("Beds");
                        String cool = documentSnapshot.getString("Cool");
                        String price = documentSnapshot.getString("Price");
                        String base64Image = documentSnapshot.getString("ImageBase64");

                        roomTypeText.setText("Type: " + type);
                        bedsText.setText("Beds: " + beds);
                        coolText.setText("AC/Non-AC: " + cool);
                        priceText.setText("Price: " + price);

                        roomTypeText.setVisibility(View.VISIBLE);
                        bedsText.setVisibility(View.VISIBLE);
                        coolText.setVisibility(View.VISIBLE);
                        priceText.setVisibility(View.VISIBLE);
                        roomDetailsLabel.setVisibility(View.VISIBLE);

                        if (base64Image != null && !base64Image.isEmpty()) {
                            try {
                                byte[] imageBytes = Base64.decode(base64Image, Base64.DEFAULT);
                                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                                roomImageView.setImageBitmap(bitmap);
                                roomImageView.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                Toast.makeText(BookRoom.this, "Failed to decode image", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            roomImageView.setImageDrawable(null);
                        }

                    } else {
                        Toast.makeText(BookRoom.this, "Room details not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(BookRoom.this, "Failed to load details", Toast.LENGTH_SHORT).show());
    }
}
