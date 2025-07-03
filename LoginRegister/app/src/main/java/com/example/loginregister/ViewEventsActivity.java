package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;

public class ViewEventsActivity extends AppCompatActivity {

    ListView listView;

    Button homebtn;
    EventListAdapter adapter;
    List<EventModel> eventList;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        homebtn = findViewById(R.id.homebtn);
        listView = findViewById(R.id.listView);
        eventList = new ArrayList<>();
        adapter = new EventListAdapter(this, eventList);
        listView.setAdapter(adapter);

        firestore = FirebaseFirestore.getInstance();

        firestore.collection("Events")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        eventList.clear();
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            EventModel event = doc.toObject(EventModel.class);
                            eventList.add(event);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Failed to load events", Toast.LENGTH_SHORT).show();
                    }
                });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewEventsActivity.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }
}
