package com.example.srswastemanager;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class SrsApplication extends Application {

    List<CreditCard> cards = new ArrayList<>();

    public List<CreditCard> getCards() {
        return cards;
    }

    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
    }


}
