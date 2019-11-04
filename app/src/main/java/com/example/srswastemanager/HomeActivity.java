package com.example.srswastemanager;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    String userID;
    Button payButton;
    Button statisticsButton;
    ImageButton newsletterButton;
    ImageButton findWasteStationButton;
    ImageButton informationButton;
    float householdWastePricePerKg = 1.37f;
    float plasticPackagingPricePerKg = 1.37f;
    float newsPapersPricePerKg = 1.37f;

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
        wastes.add(60f);    //Household Waste.
        wastes.add(6f);     //Plastic Packaging.
        wastes.add(13f);     //Newspapers.
        //---------------------------------------





        float amountDue = wastes.get(0) * householdWastePricePerKg + wastes.get(1) * plasticPackagingPricePerKg + wastes.get(2) * newsPapersPricePerKg;
        TextView amountDuetextField = (TextView) findViewById(R.id.textView5);
        amountDuetextField.setText(String.format("%.2f", amountDue) + " SEK");
        PieChart pieChart = findViewById(R.id.piechart);
        ArrayList NoOfEmp = new ArrayList();
        int i = 0;
        for (float waste : wastes) {
            NoOfEmp.add(new Entry(waste, i));
            totalWasteWeight += waste;
            i++;
        }
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
        ArrayList wasteType = new ArrayList();
        wasteType.add("Household\nWaste");
        wasteType.add("Plastic\nPackaging");
        wasteType.add("Newspapers");
        PieData data = new PieData(wasteType, dataSet);
        pieChart.setData(data);
        //pieChart.setDrawHoleEnabled(false);
        pieChart.setCenterText("Total\n"+totalWasteWeight+" kg");
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
        dataSet.setValueTextSize(13f);
        dataSet.setValueTextColor(Color.parseColor("#ffffff"));
        pieChart.animateXY(2000, 2000);

        payButton = (Button) findViewById(R.id.button13);
        statisticsButton = (Button) findViewById(R.id.button14);
        newsletterButton = (ImageButton) findViewById(R.id.imageButton);
        findWasteStationButton = (ImageButton) findViewById(R.id.imageButton2);
        informationButton = (ImageButton) findViewById(R.id.imageButton3);
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
}
