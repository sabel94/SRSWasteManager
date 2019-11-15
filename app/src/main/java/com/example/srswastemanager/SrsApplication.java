package com.example.srswastemanager;

import android.app.Application;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SrsApplication extends Application {

    static SrsApplication application;
    List<CreditCard> cards = new ArrayList<>();
    JSONObject activeUserData;


    public SrsApplication() {
        this.application = this;
    }

    public static SrsApplication getApplication() {
        return application;
    }

    public List<CreditCard> getCards() {
        return cards;
    }

    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
    }

    public JSONObject getActiveUserData() {
        return activeUserData;
    }

    public void setActiveUserData(JSONObject userData) {
        this.activeUserData = userData;
    }
}
