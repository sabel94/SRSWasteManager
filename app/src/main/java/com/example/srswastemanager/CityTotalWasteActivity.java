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

public class CityTotalWasteActivity extends AppCompatActivity {

    private ImageButton buttonLeft;
    private ImageButton buttonRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Waste Statistics");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_total_waste);

        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        data.setValueTextSize(11f);
        chart.setData(data);
        chart.setDescription("");
        chart.getXAxis().setTextSize(12f);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getLegend().setEnabled(false);
        chart.getLegend().setTextSize(12f);
        chart.animateXY(2000, 2000);
        chart.invalidate();

        buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
        buttonRight = (ImageButton) findViewById(R.id.buttonRight);

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAverageCitizenActivity();
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCityWasteFractionsActivity();
            }
        });
    }

    public void openAverageCitizenActivity() {
        Intent intent = new Intent(this, AverageCitizenActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void openCityWasteFractionsActivity() {
        Intent intent = new Intent(this, CityWasteFractionsActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(50f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(56f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(65f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(56f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(48f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(48f, 5); // Jun
        valueSet1.add(v1e6);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Your Waste Cost");
        barDataSet1.setColor(Color.parseColor("#4489ff"));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
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
