package com.example.loginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventListAdapter extends ArrayAdapter<EventModel> {

    public EventListAdapter(Context context, List<EventModel> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventModel event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvLocation = convertView.findViewById(R.id.tvLocation);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);

        tvTitle.setText(event.getTitle());
        tvDate.setText("Date: " + event.getDate());
        tvLocation.setText("Location: " + event.getLocation());
        tvDescription.setText("Description: " + event.getDescription());

        return convertView;
    }
}
