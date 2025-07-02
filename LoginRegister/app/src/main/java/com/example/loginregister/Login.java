package com.example.loginregister;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    EditText logemailtxt,logpasstxt;

    Button loglogbtn,logcanbtn,logregbtn;

    FirebaseFirestore fs;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logemailtxt = findViewById(R.id.logemailtxt);
        logpasstxt = findViewById(R.id.logpasstxt);
        logregbtn = findViewById(R.id.logregbtn);
        logcanbtn = findViewById(R.id.logcancelbtn);
        loglogbtn = findViewById(R.id.loglogbtn);
        fs = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    loglogbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (logemailtxt.getText().toString().equals("")){
                logemailtxt.setError("Type your Email");
            } else if (logpasstxt.getText().toString().equals("")) {
                logpasstxt.setError("Type your Password");
            }
            else {
                LogUser();
            }
        }
    });

    logcanbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            System.exit(0);
        }
    });


    }

    private void LogUser() {
        auth.signInWithEmailAndPassword(logemailtxt.getText().toString(), logpasstxt.getText().toString())
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(logemailtxt.getText().toString().equals("admin@gmail.com")  )
                        {
                            Intent i = new Intent(Login.this, AdminHome.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Intent i = new Intent(Login.this, Home.class);
                            startActivity(i);
                            finish();
                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setMessage("Incorrect email or password.");
                        builder.setTitle("Alert!");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                logemailtxt.setText("");
                                logpasstxt.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });
    }
}