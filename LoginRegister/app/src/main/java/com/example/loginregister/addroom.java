package com.example.loginregister;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

public class addroom extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button addbtn, homebtn, selectImageBtn;
    EditText roomnotxt, bedstxt, pricetxt;
    RadioGroup acnonac;
    Spinner spin;
    ImageView roomImage;

    String _roomtype, _roomno, _noofbeds, _price, _acnonac, base64Image;
    FirebaseFirestore fs;

    String[] roomtypes = {"Single", "Double", "Triple", "Connected", "Deluxe"};

    ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroom);

        // UI Components
        spin = findViewById(R.id.spinner);
        addbtn = findViewById(R.id.addbtn);
        homebtn = findViewById(R.id.homebtn);
        selectImageBtn = findViewById(R.id.selectImageBtn);
        roomnotxt = findViewById(R.id.roomnotxt);
        bedstxt = findViewById(R.id.bedstxt);
        acnonac = findViewById(R.id.acnonacgroup);
        pricetxt = findViewById(R.id.pricetxt);
        roomImage = findViewById(R.id.roomImage);

        fs = FirebaseFirestore.getInstance();

        // Spinner setup
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roomtypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        // Image picker launcher
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        roomImage.setImageURI(imageUri);
                        convertImageToBase64(imageUri);
                    }
                });

        selectImageBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });

        addbtn.setOnClickListener(view -> {
            _roomno = roomnotxt.getText().toString();
            _noofbeds = bedstxt.getText().toString();
            _price = pricetxt.getText().toString();

            int selectedRadio = acnonac.getCheckedRadioButtonId();
            if (selectedRadio != -1) {
                RadioButton radioButton = findViewById(selectedRadio);
                _acnonac = radioButton.getText().toString();
            }

            if (_roomno.isEmpty() || _noofbeds.isEmpty() || _price.isEmpty() || base64Image == null) {
                Toast.makeText(addroom.this, "Please fill all fields and select an image.", Toast.LENGTH_SHORT).show();
                return;
            }

            HashMap<String, Object> roomData = new HashMap<>();
            roomData.put("Type", _roomtype);
            roomData.put("No", _roomno);
            roomData.put("Beds", _noofbeds);
            roomData.put("Cool", _acnonac);
            roomData.put("Price", _price);
            roomData.put("ImageBase64", base64Image);

            DocumentReference docRef = fs.collection("Rooms").document(_roomno);
            docRef.set(roomData, SetOptions.merge())
                    .addOnSuccessListener(unused -> Toast.makeText(addroom.this, "Room added", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(addroom.this, "Failed to add room", Toast.LENGTH_SHORT).show());
        });

        homebtn.setOnClickListener(v -> {
            Intent i = new Intent(addroom.this, AdminHome.class);
            startActivity(i);
            finish();
        });


    }

    private void convertImageToBase64(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);  // compress to reduce size
            byte[] imageBytes = baos.toByteArray();
            base64Image = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to convert image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _roomtype = spin.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
