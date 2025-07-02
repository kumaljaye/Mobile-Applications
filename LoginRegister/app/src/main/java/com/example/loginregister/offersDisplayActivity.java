package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class offersDisplayActivity extends AppCompatActivity {

    ArrayList<OffersModel> offeresModelArrayList;
    ListView offeresLV;

    Button homebtn,bookbtn;
    FirebaseAuth auth;
    FirebaseFirestore fs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_display);

        offeresLV = findViewById(R.id.offerslist);
        homebtn = findViewById(R.id.homebtn);
        bookbtn = findViewById(R.id.bookbtn);
        fs = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        offeresModelArrayList = new ArrayList<OffersModel>();

        loadDatainListView();


    }

    private void loadDatainListView()
    {
        fs.collection("Offeres").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                    {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list)
                            {
                                OffersModel offeresmodel = d.toObject(OffersModel.class) ;
                                offeresModelArrayList.add(offeresmodel);
                            }

                            OffersLVAdapter adapter = new OffersLVAdapter(offersDisplayActivity.this,offeresModelArrayList);
                            offeresLV.setAdapter(adapter);
                        }
                    }
                });



        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(offersDisplayActivity.this, Home.class);
                startActivity(i);
                finish();
            }
        });

    }
}