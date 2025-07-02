package com.example.loginregister;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class OffersLVAdapter extends ArrayAdapter<OffersModel>
{
    public OffersLVAdapter(@NonNull Context context, ArrayList<OffersModel> offeresModelArrayList) {
        super(context, 0, offeresModelArrayList);
    }

    public View getView (int position,View convertView, ViewGroup parent)
    {
        View listitemView = convertView;
        if(listitemView == null)
        {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.offerresourcefile , parent,false);
        }

        OffersModel offeresModel = getItem(position);
        TextView offername = listitemView.findViewById(R.id.offernametxt);
        TextView offerprice = listitemView.findViewById(R.id.offerpricetxt);


        offername.setText(offeresModel.getOfferName());
        offerprice.setText(String.format("LKR %d", offeresModel.getOfferPrice()));

        return  listitemView;
    }
}
