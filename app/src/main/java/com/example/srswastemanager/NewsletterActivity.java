package com.example.srswastemanager;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsletterActivity extends AppCompatActivity {

    String q1 = "2019-10-29 10:23\nInformation about waste stations";
    String a1 = "\nHi citizens of Stockholm Royal Seaport,\n\nLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
    String q2 = "2019-10-05 16:07\nTrash being left on the ground";
    String a2 = "\nHi citizens of Stockholm Royal Seaport,\n\nLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
    String q3 = "2019-09-15 08:46\nSystem maintenance September 25th";
    String a3 = "\nHi citizens of Stockholm Royal Seaport,\n\nLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
    String q4 = "2019-07-12 13:35\nDon't use too large garbage bags";
    String a4 = "\nHi citizens of Stockholm Royal Seaport,\n\nLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Newsletter");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter);

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
                ImageView newsletterIcon1 = (ImageView) findViewById(R.id.imageView11);
                newsletterIcon1.setColorFilter(Color.parseColor("#7f7f7f"));
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
                ImageView newsletterIcon2 = (ImageView) findViewById(R.id.imageView12);
                newsletterIcon2.setColorFilter(Color.parseColor("#7f7f7f"));
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
                ImageView newsletterIcon3 = (ImageView) findViewById(R.id.imageView13);
                newsletterIcon3.setColorFilter(Color.parseColor("#7f7f7f"));
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
                ImageView newsletterIcon4 = (ImageView) findViewById(R.id.imageView14);
                newsletterIcon4.setColorFilter(Color.parseColor("#7f7f7f"));
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
