package com.example.pictureapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity2 extends AppCompatActivity {

    EditText disemailtxt;

    ImageView dispic;

    TextView disname;

    Button displaybtn;

    FirebaseFirestore fs;

    String sImage;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        disemailtxt = findViewById(R.id.disemailtxt);
        disname = findViewById(R.id.disnametxt);
        displaybtn = findViewById(R.id.displaybtn);
        dispic = findViewById(R.id.picview);
        fs = FirebaseFirestore.getInstance();

        displaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fs.collection("Pictures").document(disemailtxt.getText().toString())
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    DocumentSnapshot doc = task.getResult();
                                    disname.setText(doc.getString("Name"));
                                    sImage = doc.getString("Picture");

                                //decode base64 string
                                    byte[] bytes = Base64.decode(sImage,Base64.DEFAULT);
                                    bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                                    dispic.setImageBitmap(bitmap);
                                }
                            }
                        });
            }
        });

    }
}