package com.example.srswastemanager;

import java.util.Locale;

class SekValueFormatter implements com.github.mikephil.charting.utils.ValueFormatter {
    @Override
    public String getFormattedValue(float value) {
        return String.format(Locale.getDefault(), "%.2f", value);
    }
}
