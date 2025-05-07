package com.example.fristapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText num1,num2,result;
    Button addbtn, subbtn ,mulbtn, divbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        result = findViewById(R.id.result);
        addbtn = findViewById(R.id.addbtn);
        subbtn = findViewById(R.id.subbtn);
        mulbtn = findViewById(R.id.mulbtn);
        divbtn = findViewById(R.id.divbtn);

    addbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int x = Integer.parseInt(num1.getText().toString());
            int y = Integer.parseInt(num2.getText().toString());

            int sum = x + y;
            String output = String.valueOf(sum);
            result.setText(output);
        }
    });

        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(num1.getText().toString());
                int y = Integer.parseInt(num2.getText().toString());

                int sub = x - y;
                String output = String.valueOf(sub);
                result.setText(output);
            }
        });

        mulbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(num1.getText().toString());
                int y = Integer.parseInt(num2.getText().toString());

                int mul = x * y;
                String output = String.valueOf(mul);
                result.setText(output);
            }
        });

        divbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(num1.getText().toString());
                int y = Integer.parseInt(num2.getText().toString());

                int div = x / y;
                String output = String.valueOf(div);
                result.setText(output);
            }
        });

    }
}