package com.example.loginregister;

public class OffersModel {
    String offerName;
    int offerPrice;

    public OffersModel(){

    }

    public OffersModel(String offerName, int offerPrice){
        this.offerName = offerName;
        this.offerPrice = offerPrice;
    }

    public  String GetOfferName(){
        return offerName;
    }

    public int GetOfferPrice(){
        return offerPrice;
    }
}
