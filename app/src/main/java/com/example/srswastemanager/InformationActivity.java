package com.example.srswastemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    String q1 = "What can I throw at the waste stations?";
    String a1 = "This is some random text that will be replaced by a relevant answer.";
    String q2 = "How big garbage bags can I use?";
    String a2 = "This is some random text that will be replaced by a relevant answer.";
    String q3 = "How do I open the disposal chutes?";
    String a3 = "This is some random text that will be replaced by a relevant answer.";
    String q4 = "Who do I contact if the waste station system is out of order?";
    String a4 = "This is some random text that will be replaced by a relevant answer.";

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
    }
}
