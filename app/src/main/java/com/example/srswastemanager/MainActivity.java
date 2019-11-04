package com.example.srswastemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("BankID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continueButton = (ImageButton) findViewById(R.id.bankIDButton1);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }
}
