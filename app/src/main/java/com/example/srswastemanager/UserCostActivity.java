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
import com.github.mikephil.charting.utils.DefaultValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.srswastemanager.SrsApplication.getCurrentMonth;
import static com.example.srswastemanager.SrsApplication.getCurrentYear;

public class UserCostActivity extends AppCompatActivity {

    private ImageButton buttonLeft;
    private ImageButton buttonRight;
    static final int NUMBER_OF_MONTHS = 6;

    static final String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Waste Statistics");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cost);

        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(-1), getDataSet());
        data.setValueFormatter(new SekValueFormatter());
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
                openCityWasteFractionsActivity();
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAverageCitizenActivity();
            }
        });
    }

    public void openCityWasteFractionsActivity() {
        Intent intent = new Intent(this, CityWasteFractionsActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void openAverageCitizenActivity() {
        Intent intent = new Intent(this, AverageCitizenActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        List<BarEntry> valueSet = null;
        valueSet = getBarEntries();
        BarDataSet barDataSet = new BarDataSet(valueSet, "Your Waste Cost");
        barDataSet.setColor(Color.parseColor("#FDCA40"));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        return dataSets;
    }

    private List<BarEntry> getBarEntries() {
        List<JSONObject> paymentsToShowInCurrentYear
                = getPaymentsInYearUpTo(getCurrentYear(), getCurrentMonth(), NUMBER_OF_MONTHS);
        if (paymentsToShowInCurrentYear.size() < NUMBER_OF_MONTHS) {
            paymentsToShowInCurrentYear.addAll(
                    getPaymentsInYearUpTo(
                            getCurrentYear() - 1,
                            11,
                            5 - paymentsToShowInCurrentYear.size()));
        }

        return IntStream.range(0, NUMBER_OF_MONTHS).mapToObj(i -> {
            try {
                return new BarEntry((float) paymentsToShowInCurrentYear.get(i).getDouble("total_amount"), i);
            } catch (JSONException e) {
                return new BarEntry(0f, i);
            }
        }).collect(Collectors.toList());
    }

    private List<JSONObject> getPaymentsInYearUpTo(int year, int month, int numberOfMonths) {
        List<JSONObject> payments = new ArrayList<>();
        try {
            JSONArray paymentsInYear = ((SrsApplication) getApplication()).getActiveUserData().getJSONObject("payments").getJSONArray(Integer.toString(year));
            for (int i = month - numberOfMonths + 1; i <= month; i++) {
                payments.add(paymentsInYear.getJSONObject(i));
            }
            return payments;
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    static ArrayList<String> getXAxisValues(int offset) {
        ArrayList<String> xAxis = new ArrayList<>();
        IntStream.range(0, NUMBER_OF_MONTHS).forEach(i -> {
            xAxis.add(months[getCurrentMonth() - (NUMBER_OF_MONTHS - i + offset)]);
        });
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
