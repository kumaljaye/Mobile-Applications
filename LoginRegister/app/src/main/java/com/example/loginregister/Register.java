package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText emailtxt,passtxt,confirmtxt,nametxt,phonetxt;

    Button regbtn,cancelbtn,logbtn;

    FirebaseFirestore fs;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailtxt = findViewById(R.id.emailtxt);
        passtxt = findViewById(R.id.passtxt);
        confirmtxt = findViewById(R.id.confirmtxt);
        nametxt = findViewById(R.id.nametxt);
        phonetxt = findViewById(R.id.phonetxt);
        fs = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        regbtn = findViewById(R.id.regbtn);
        logbtn = findViewById(R.id.logbtn);
        cancelbtn = findViewById(R.id.cancelbtn);


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailtxt.getText().toString().equals("")){
                    emailtxt.setError("Type your Email");
                } else if (passtxt.getText().toString().equals("")) {
                    passtxt.setError("Type your Password");
                } else if (confirmtxt.getText().toString().equals("")) {
                    confirmtxt.setError("Re-type your password");
                } else if (!(passtxt.getText().toString().equals(confirmtxt.getText().toString()))) {
                    confirmtxt.setError("Password does not match");
                } else if (nametxt.getText().toString().equals("")) {
                    nametxt.setError("Type your name");
                } else if (phonetxt.getText().toString().equals("")) {
                    phonetxt.setError("Type your Phone No.");
                }
                else {
                    RegisterNewUser();
                }
            }


        });

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });



    }

    private void RegisterNewUser() {
        auth.createUserWithEmailAndPassword(emailtxt.getText().toString(), passtxt.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("Email", emailtxt.getText().toString());
                            hashMap.put("Name", nametxt.getText().toString());
                            hashMap.put("Phone", phonetxt.getText().toString());

                            FirebaseFirestore.getInstance()
                                    .collection("users")
                                    .document(uid)
                                    .set(hashMap, SetOptions.merge());

                            Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(Register.this, Login.class);
                            startActivity(i);
                            finish();
                        } else {
                            Exception e = task.getException();
                            e.printStackTrace();
                            Toast.makeText(Register.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}