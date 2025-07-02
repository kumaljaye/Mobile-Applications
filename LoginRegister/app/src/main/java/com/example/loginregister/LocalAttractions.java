package com.example.loginregister;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocalAttractions extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4, imageView5;
    TextView textView1, textView2, textView3, textView4, textView5;
    TextView desc1, desc2, desc3, desc4, desc5;

    Button homebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_attractions);

        homebtn = findViewById(R.id.homebtn);

        imageView1 = findViewById(R.id.imageView1);
        textView1 = findViewById(R.id.textView1);
        desc1 = findViewById(R.id.desc1);


        imageView2 = findViewById(R.id.imageView2);
        textView2 = findViewById(R.id.textView2);
        desc2 = findViewById(R.id.desc2);

        imageView3 = findViewById(R.id.imageView3);
        textView3 = findViewById(R.id.textView3);
        desc3 = findViewById(R.id.desc3);

        imageView4 = findViewById(R.id.imageView4);
        textView4 = findViewById(R.id.textView4);
        desc4 = findViewById(R.id.desc4);

        imageView5 = findViewById(R.id.imageView5);
        textView5 = findViewById(R.id.textView5);
        desc5 = findViewById(R.id.desc5);




        textView1.setText("Temple of the Tooth");
        desc1.setText("One of the most sacred Buddhist sites, located in Kandy.");

        textView2.setText("Galle Fort");
        desc2.setText("A UNESCO World Heritage Site on Sri Lanka’s southwest coast.");

        textView3.setText("Sigiriya Rock");
        desc3.setText("Ancient rock fortress with amazing views and history.");

        textView4.setText("Sigiriya Rock");
        desc4.setText("Ancient rock fortress with amazing views and history.");

        textView5.setText("Sigiriya Rock");
        desc5.setText("Ancient rock fortress with amazing views and history.");

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocalAttractions.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }
}
