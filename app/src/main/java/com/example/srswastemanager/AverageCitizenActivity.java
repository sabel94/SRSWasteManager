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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.srswastemanager.SrsApplication.getCurrentMonth;
import static com.example.srswastemanager.SrsApplication.getCurrentYear;
import static com.example.srswastemanager.UserCostActivity.NUMBER_OF_MONTHS;
import static com.example.srswastemanager.UserCostActivity.getXAxisValues;


public class AverageCitizenActivity extends AppCompatActivity {

    private ImageButton buttonLeft;  // TODO: remove decimals and make rolling for current month
    private ImageButton buttonRight;
    int offset = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Waste Statistics");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_citizen);

        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(offset), getDataSet());
        data.setValueFormatter(new IntValueFormatter());
        data.setValueTextSize(14f);
        chart.setData(data);
        chart.setDescription("");
        chart.getXAxis().setTextSize(14f);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getLegend().setTextSize(14f);
        chart.animateXY(2000, 2000);
        chart.invalidate();

        buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
        buttonRight = (ImageButton) findViewById(R.id.buttonRight);

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserCostActivity();
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCityTotalWasteActivity();
            }
        });
    }

    public void openUserCostActivity() {
        Intent intent = new Intent(this, UserCostActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    public void openCityTotalWasteActivity() {
        Intent intent = new Intent(this, CityTotalWasteActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets;
        List<BarEntry> userWaste = wasteMonthsToBarEntries(getLatestUserWasteMonths());
        List<BarEntry> averageWaste
                = wasteAveragesToBarEntries(SrsApplication.getApplication().getAverageWasteAmounts());
        BarDataSet barDataSet1 = new BarDataSet(userWaste, "You");
        barDataSet1.setColor(Color.rgb(68, 138, 255));
        BarDataSet barDataSet2 = new BarDataSet(averageWaste, "Average Household");
        barDataSet2.setColor(Color.rgb(233, 30, 99));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private List<BarEntry> wasteAveragesToBarEntries(Map<String, List<Float>> averageWasteAmounts) {
        String year = Integer.toString(getCurrentYear());
        List<Float> currentYearAverages = averageWasteAmounts.get(year);
        List<Float> averages;
        if (currentYearAverages.size() < NUMBER_OF_MONTHS) {
             averages = averageWasteAmounts
                    .get(Integer.toString(getCurrentYear() - 1))
                    .subList(12 - (NUMBER_OF_MONTHS - currentYearAverages.size()), 12);
             averages.addAll(currentYearAverages);
        } else {
            averages = currentYearAverages.subList(getCurrentMonth() - (NUMBER_OF_MONTHS) - offset, getCurrentMonth() - offset);
        }
        return IntStream
                .range(0, NUMBER_OF_MONTHS)
                .mapToObj(i -> new BarEntry(averages.get(i), i))
                .collect(Collectors.toList());
    }

    private List<BarEntry> wasteMonthsToBarEntries(List<JSONObject> wasteMonths) {
        return IntStream.range(0, NUMBER_OF_MONTHS).mapToObj(i -> {
            try {
                return new BarEntry(calculateSumOfFractions(wasteMonths.get(i)), i);
            } catch (JSONException e) {
                return new BarEntry(0f, i);
            }
        }).collect(Collectors.toList());
    }

    private float calculateSumOfFractions(JSONObject wasteMonth) throws JSONException {
        return (float) (wasteMonth.getDouble("householdWaste") + wasteMonth.getDouble("plasticPackaging") + wasteMonth.getDouble("newspapers"));
    }

    private List<JSONObject> getLatestUserWasteMonths() {
        List<JSONObject> latestUserWasteMonths = getUserWastePerMonthUpTo(getCurrentYear(), getCurrentMonth(), UserCostActivity.NUMBER_OF_MONTHS);
        if (latestUserWasteMonths.size() < UserCostActivity.NUMBER_OF_MONTHS) {
            latestUserWasteMonths.addAll(getUserWastePerMonthUpTo(getCurrentYear() - 1, 11, 5 - latestUserWasteMonths.size()));
        }
        return latestUserWasteMonths;
    }

    private List<JSONObject> getUserWastePerMonthUpTo(int year, int month, int numberOfMonths) {
        List<JSONObject> wasteMonths = new ArrayList<>();
        try {
            JSONArray wasteStatsInYear = ((SrsApplication) getApplication()).getActiveUserData().getJSONObject("wasteStats").getJSONArray(Integer.toString(year));
            for (int i = month - numberOfMonths + 1; i <= month; i++) {
                wasteMonths.add(wasteStatsInYear.getJSONObject(i));
            }
            return wasteMonths;
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
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
