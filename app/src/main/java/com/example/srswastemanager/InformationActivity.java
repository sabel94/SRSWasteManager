package com.example.srswastemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    String q1 = "What is the purpose of this app?";
    String a1 = "The SRS Waste Manager app will assist and improve your waste management. It enables you to track and pay for your waste amount, but also provides tools that make it easier to understand and use the waste system in SRS.";
    String q2 = "What can I throw at the waste stations?";
    String a2 = "You can throw Household waste, Plastic packaging and Newspapers.";
    String q3 = "How big waste sacks can I use?";
    String a3 = "This is some random text that will be replaced by a relevant answer.";
    String q4 = "How do I open the waste disposal chutes?";
    String a4 = "The disposal chutes can be opened with your RFID tag.";
    String q5 = "What should I do if a disposal chute seems to be full despite having the status 'Available'?";
    String a5 = "Try to push down the top layer. Some object is probably blocking the pipe.";
    String q6 = "Who do I contact if a disposal chute is out of order?";
    String a6 = "This is some random text that will be replaced by a relevant answer.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Waste Management FAQ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        CardView faq1 = (CardView) findViewById(R.id.faq1);
        TextView question1 = (TextView) findViewById(R.id.question1);
        final TextView answer1 = (TextView) findViewById(R.id.answer1);
        question1.setText(q1);
        answer1.setText(a1);
        final ImageView arrow1 = (ImageView) findViewById(R.id.imageView1);
        arrow1.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
        faq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer1.getVisibility() == View.GONE) {
                    answer1.setVisibility(View.VISIBLE);
                    arrow1.setBackgroundResource(0);
                    arrow1.setBackgroundResource(R.drawable.outline_keyboard_arrow_up_24);
                } else {
                    answer1.setVisibility(View.GONE);
                    arrow1.setBackgroundResource(0);
                    arrow1.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
                }
            }
        });

        CardView faq2 = (CardView) findViewById(R.id.faq2);
        TextView question2 = (TextView) findViewById(R.id.question2);
        final TextView answer2 = (TextView) findViewById(R.id.answer2);
        question2.setText(q2);
        answer2.setText(a2);
        final ImageView arrow2 = (ImageView) findViewById(R.id.imageView2);
        arrow2.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
        faq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer2.getVisibility() == View.GONE) {
                    answer2.setVisibility(View.VISIBLE);
                    arrow2.setBackgroundResource(0);
                    arrow2.setBackgroundResource(R.drawable.outline_keyboard_arrow_up_24);
                } else {
                    answer2.setVisibility(View.GONE);
                    arrow2.setBackgroundResource(0);
                    arrow2.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
                }
            }
        });

        CardView faq3 = (CardView) findViewById(R.id.faq3);
        TextView question3 = (TextView) findViewById(R.id.question3);
        final TextView answer3 = (TextView) findViewById(R.id.answer3);
        question3.setText(q3);
        answer3.setText(a3);
        final ImageView arrow3 = (ImageView) findViewById(R.id.imageView3);
        arrow3.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
        faq3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer3.getVisibility() == View.GONE) {
                    answer3.setVisibility(View.VISIBLE);
                    arrow3.setBackgroundResource(0);
                    arrow3.setBackgroundResource(R.drawable.outline_keyboard_arrow_up_24);
                } else {
                    answer3.setVisibility(View.GONE);
                    arrow3.setBackgroundResource(0);
                    arrow3.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
                }
            }
        });

        CardView faq4 = (CardView) findViewById(R.id.faq4);
        TextView question4 = (TextView) findViewById(R.id.question4);
        final TextView answer4 = (TextView) findViewById(R.id.answer4);
        question4.setText(q4);
        answer4.setText(a4);
        final ImageView arrow4 = (ImageView) findViewById(R.id.imageView4);
        arrow4.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
        faq4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer4.getVisibility() == View.GONE) {
                    answer4.setVisibility(View.VISIBLE);
                    arrow4.setBackgroundResource(0);
                    arrow4.setBackgroundResource(R.drawable.outline_keyboard_arrow_up_24);
                } else {
                    answer4.setVisibility(View.GONE);
                    arrow4.setBackgroundResource(0);
                    arrow4.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
                }
            }
        });

        CardView faq5 = (CardView) findViewById(R.id.faq5);
        TextView question5 = (TextView) findViewById(R.id.question5);
        final TextView answer5 = (TextView) findViewById(R.id.answer5);
        question5.setText(q5);
        answer5.setText(a5);
        final ImageView arrow5 = (ImageView) findViewById(R.id.imageView5);
        arrow5.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
        faq5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer5.getVisibility() == View.GONE) {
                    answer5.setVisibility(View.VISIBLE);
                    arrow5.setBackgroundResource(0);
                    arrow5.setBackgroundResource(R.drawable.outline_keyboard_arrow_up_24);
                } else {
                    answer5.setVisibility(View.GONE);
                    arrow5.setBackgroundResource(0);
                    arrow5.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
                }
            }
        });

        CardView faq6 = (CardView) findViewById(R.id.faq6);
        TextView question6 = (TextView) findViewById(R.id.question6);
        final TextView answer6 = (TextView) findViewById(R.id.answer6);
        question6.setText(q6);
        answer6.setText(a6);
        final ImageView arrow6 = (ImageView) findViewById(R.id.imageView6);
        arrow6.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
        faq6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer6.getVisibility() == View.GONE) {
                    answer6.setVisibility(View.VISIBLE);
                    arrow6.setBackgroundResource(0);
                    arrow6.setBackgroundResource(R.drawable.outline_keyboard_arrow_up_24);
                } else {
                    answer6.setVisibility(View.GONE);
                    arrow6.setBackgroundResource(0);
                    arrow6.setBackgroundResource(R.drawable.outline_keyboard_arrow_down_24);
                }
            }
        });
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
