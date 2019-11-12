package com.example.srswastemanager;

import android.app.Application;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SrsApplication extends Application {

    List<CreditCard> cards = new ArrayList<>();
    JSONObject userData;



    public List<CreditCard> getCards() {
        return cards;
    }

    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
    }

    public JSONObject getUserData() {
        return userData;
    }

    public void setUserData(JSONObject userData) {
        this.userData = userData;
    }
}
