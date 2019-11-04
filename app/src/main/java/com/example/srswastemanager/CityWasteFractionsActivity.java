package com.example.srswastemanager;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class CityWasteFractionsActivity extends AppCompatActivity {

    private ImageButton buttonLeft;
    private ImageButton buttonRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Waste Statistics");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_waste_fractions);

        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        data.setValueTextSize(11f);
        chart.setData(data);
        chart.setDescription("");
        chart.getXAxis().setTextSize(12f);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getLegend().setTextSize(12f);
        chart.animateXY(2000, 2000);
        chart.invalidate();

        buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
        buttonRight = (ImageButton) findViewById(R.id.buttonRight);

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCityTotalWasteActivity();
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserCostActivity();
            }
        });
    }

    public void openCityTotalWasteActivity() {
        Intent intent = new Intent(this, CityTotalWasteActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void openUserCostActivity() {
        Intent intent = new Intent(this, UserCostActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(43f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(47f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(48f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(48f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(38f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(41f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(3f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(3f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(8f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(3f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(5f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(3f, 5); // Jun
        valueSet2.add(v2e6);

        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry v3e1 = new BarEntry(4f, 0); // Jan
        valueSet3.add(v3e1);
        BarEntry v3e2 = new BarEntry(5f, 1); // Feb
        valueSet3.add(v3e2);
        BarEntry v3e3 = new BarEntry(9f, 2); // Mar
        valueSet3.add(v3e3);
        BarEntry v3e4 = new BarEntry(5f, 3); // Apr
        valueSet3.add(v3e4);
        BarEntry v3e5 = new BarEntry(5f, 4); // May
        valueSet3.add(v3e5);
        BarEntry v3e6 = new BarEntry(4f, 5); // Jun
        valueSet3.add(v3e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Household Waste");
        barDataSet1.setColor(Color.parseColor("#4489ff"));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Plastic Packaging");
        barDataSet2.setColor(Color.parseColor("#FF3366"));
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Newspapers");
        barDataSet3.setColor(Color.parseColor("#2EC4B6"));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        xAxis.add("JUL");
        xAxis.add("AUG");
        return xAxis;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            return true;
        }
        return false;
    }
}
