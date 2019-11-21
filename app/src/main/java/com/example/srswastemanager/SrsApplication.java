package com.example.srswastemanager;

import android.app.Application;
import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class SrsApplication extends Application {

    static SrsApplication application;

    static Context mContext;

    List<CreditCard> cards = new ArrayList<>();

    JSONObject activeUserData;
    Map<String, List<Float>> averageWasteAmounts;

    static String[] months = {"january", "february", "march", "april", "may", "june", "juli", "august", "september", "october", "november", "december"};

    public SrsApplication() {
        this.application = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
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

    static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public Map<String, List<Float>> getAverageWasteAmounts() {
        return averageWasteAmounts;
    }

    public void setAverageWasteAmounts(Map<String, List<Float>> averageWasteAmounts) {
        this.averageWasteAmounts = averageWasteAmounts;
    }
}