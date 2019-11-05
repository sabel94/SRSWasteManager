package com.example.srswastemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.stream.Collectors;

public class CreditCardListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button newCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<CreditCard> cards = ((SrsApplication) getApplication()).getCards();
        adapter = new CreditCardAdapter(cards.stream().map(CreditCard::getCardName).collect(Collectors.toList()));
        recyclerView.setAdapter(adapter);

        newCard = findViewById(R.id.button17);
        newCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCreditCardActivity();
            }
        });
    }

    private void openAddCreditCardActivity() {
        Intent intent = new Intent(this, AddCreditCardActivity.class);
        startActivity(intent);
    }
}
