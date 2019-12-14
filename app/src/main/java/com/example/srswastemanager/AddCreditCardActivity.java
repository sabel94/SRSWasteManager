package com.example.srswastemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class AddCreditCardActivity extends AppCompatActivity {

    TextInputEditText cardName;
    TextInputEditText cardNumber;
    TextInputEditText cvv;
    TextInputEditText month;
    TextInputEditText year;
    Button submitButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Add Bank Card");
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);


        cardName = (TextInputEditText) findViewById(R.id.textInputEditText1);
        cardNumber = (TextInputEditText) findViewById(R.id.textInputEditText2);
        cvv = (TextInputEditText) findViewById(R.id.textInputEditText3);
        month = (TextInputEditText) findViewById(R.id.textInputEditText4);
        year = (TextInputEditText) findViewById(R.id.textInputEditText5);
        submitButton = (Button) findViewById(R.id.button16);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCard();
            }
        });
    }

    private void submitCard() {
        SrsApplication app = (SrsApplication) getApplication();
        List<CreditCard> cards = app.getCards();

        // TODO: Validate form

        CreditCard creditCard = new CreditCard(cardName.getText().toString(), cardNumber.getText().toString(), cvv.getText().toString(), month.getText().toString(), year.getText().toString());
        cards.add(creditCard);

        Intent intent = new Intent(this, CreditCardListActivity.class);
        startActivity(intent);
    }
}
