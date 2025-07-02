package com.example.loginregister;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class addroom extends AppCompatActivity implements AdapterView.OnItemSelectedListener, room {


    Button addbtn,cancelbtn;

    EditText roomnotxt, bedstxt, pricetxt;

    RadioGroup acnonac;

    FirebaseFirestore fs;

    Spinner spin;

    String _roomtype, _roomno, _noofbeds, _price, _acnonac;

    String[] roomtypes = {"Single","Double","Triple","Connected","Deluxe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroom);
        spin = findViewById(R.id.spinner);
        addbtn = findViewById(R.id.addbtn);
        cancelbtn = findViewById(R.id.cancelbtn);
        roomnotxt = findViewById(R.id.roomnotxt);
        bedstxt = findViewById(R.id.bedstxt);
        acnonac = findViewById(R.id.acnonacgroup);
        pricetxt = findViewById(R.id.pricetxt);

        fs = FirebaseFirestore.getInstance();

        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,roomtypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                _roomno = roomnotxt.getText().toString();
                _noofbeds = bedstxt.getText().toString();
                _price = pricetxt.getText().toString();

                int selectedRadio = acnonac.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedRadio);
                _acnonac = radioButton.getText().toString();

                HashMap<Object,String> hashMap = new HashMap<>();
                hashMap.put("Type", _roomtype);
                hashMap.put("No", _roomno);
                hashMap.put("Beds", _noofbeds);
                hashMap.put("Cool", _acnonac);
                hashMap.put("Price", _price);
                DocumentReference docRef = fs.collection("Rooms")
                        .document(_roomno);
                docRef.set(hashMap, SetOptions.merge());
            }
        });



    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _roomtype = spin.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){

    }
}