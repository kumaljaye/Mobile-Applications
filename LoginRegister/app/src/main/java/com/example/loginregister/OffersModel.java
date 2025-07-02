package com.example.loginregister;

import com.google.firebase.firestore.PropertyName;

public class OffersModel {
    private String offerName;
    private int offerPrice;

    public OffersModel() {}

    public OffersModel(String offerName, int offerPrice) {
        this.offerName = offerName;
        this.offerPrice = offerPrice;
    }

    @PropertyName("OfferName")
    public String getOfferName() {
        return offerName;
    }
    @PropertyName("OfferName")
    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    @PropertyName("OfferPrice")
    public int getOfferPrice() {
        return offerPrice;
    }
    @PropertyName("OfferPrice")
    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }
}