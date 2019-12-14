package com.example.srswastemanager;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static com.example.srswastemanager.SrsApplication.getCurrentMonth;
import static com.example.srswastemanager.SrsApplication.getCurrentYear;

public class HomeActivity extends AppCompatActivity {

    String userID;
    Button payButton;
    Button statisticsButton;
    ImageButton newsletterButton;
    ImageButton findWasteStationButton;
    ImageButton informationButton;
    ImageButton creditCardButton;
    TextView month;
    float wastePricePerKg = 2.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
        }

        //-----------Data Reading---------------
        float totalWasteWeight = 0f;
        ArrayList<Float> wastes = new ArrayList<Float>();
        Calendar instance = Calendar.getInstance();
        int currentMonth = instance.get(Calendar.MONTH);
        JSONObject monthData;
        try {
            monthData = ((SrsApplication) getApplication()).getActiveUserData()
                    .getJSONObject("wasteStats")
                    .getJSONArray(Integer.toString(getCurrentYear()))
                    .getJSONObject(getCurrentMonth());
            wastes.add((float) monthData.getDouble("householdWaste"));    //Household Waste.
            wastes.add((float) monthData.getDouble("plasticPackaging"));     //Plastic Packaging.
            wastes.add((float) monthData.getDouble("newspapers"));    //Newspapers.
        } catch (JSONException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //---------------------------------------

        month = (TextView) findViewById(R.id.textView2);
        month.setText(String.format("%s %s", StringUtils.capitalize(SrsApplication.months[currentMonth]), instance.get(Calendar.YEAR)));

        float amountDue = (wastes.get(0) + wastes.get(1) + wastes.get(2)) * wastePricePerKg;
        TextView amountDuetextField = (TextView) findViewById(R.id.textView5);
        amountDuetextField.setText(String.format(Locale.getDefault(), "%.2f SEK", amountDue));
        PieChart pieChart = findViewById(R.id.piechart);
        ArrayList NoOfEmp = new ArrayList();
        int i = 0;
        for (float waste : wastes) {
            NoOfEmp.add(new Entry(waste, i));
            totalWasteWeight += waste;
            i++;
        }
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
        dataSet.setValueFormatter(new IntValueFormatter());
        ArrayList wasteType = new ArrayList();
        wasteType.add("Household\nWaste");
        wasteType.add("Plastic\nPackaging");
        wasteType.add("Newspapers");
        PieData data = new PieData(wasteType, dataSet);
        pieChart.setData(data);
        //pieChart.setDrawHoleEnabled(false);
        pieChart.setCenterText(String.format(Locale.getDefault(), "Total\n %d kg", IntValueFormatter.roundToInt(totalWasteWeight)));
        pieChart.setCenterTextSize(16f);
        pieChart.setTransparentCircleRadius(28f);
        pieChart.setDescription("");
        pieChart.setHoleRadius(26f);
        pieChart.getLegend().setEnabled(false);
        //pieChart.setHoleColor(Color.parseColor("#4489ff"));
        dataSet.setColors(new int[]{
                Color.parseColor("#4489ff"),
                Color.parseColor("#FF3366"),
                Color.parseColor("#2EC4B6")});
        dataSet.setValueTextSize(13.25f);
        dataSet.setValueTextColor(Color.parseColor("#ffffff"));
        pieChart.animateXY(2000, 2000);

        payButton = (Button) findViewById(R.id.button13);
        statisticsButton = (Button) findViewById(R.id.button14);
        newsletterButton = (ImageButton) findViewById(R.id.imageButton);
        findWasteStationButton = (ImageButton) findViewById(R.id.imageButton2);
        informationButton = (ImageButton) findViewById(R.id.imageButton3);
        creditCardButton = (ImageButton) findViewById(R.id.imageButton4);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPayActivity();
            }
        });
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatisticsActivity();
            }
        });
        newsletterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewsletterActivity();
            }
        });
        findWasteStationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFindWasteStationActivity();
            }
        });
        informationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInformationActivity();
            }
        });
        creditCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreditCardListActivity();
            }
        });
    }

    public void openPayActivity() {
        Intent intent = new Intent(this, PayActivity.class);
        startActivity(intent);
    }

    public void openStatisticsActivity() {
        Intent intent = new Intent(this, UserCostActivity.class);
        startActivity(intent);
    }

    public void openNewsletterActivity() {
        Intent intent = new Intent(this, NewsletterActivity.class);
        startActivity(intent);
    }

    public void openFindWasteStationActivity() {
        Intent intent = new Intent(this, FindWasteStationActivity.class);
        startActivity(intent);
    }

    public void openInformationActivity() {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    public void openCreditCardListActivity() {
        Intent intent = new Intent(this, CreditCardListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            return true;
        }
        return false;
    }
}
