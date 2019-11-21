package com.example.srswastemanager;

import com.github.mikephil.charting.utils.ValueFormatter;

public class IntValueFormatter implements ValueFormatter {
    @Override
    public String getFormattedValue(float value) {
        return Integer.toString(roundToInt(value));
    }

    public static int roundToInt(float value) {
        return value - ((int) value) < 0.5 ? (int) value : (int) value + 1;
    }
}
