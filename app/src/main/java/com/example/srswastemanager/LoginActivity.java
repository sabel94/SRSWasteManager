package com.example.srswastemanager;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {

    TextView name;
    Button loginButton;
    Button eraseButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    TextView textView;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("BankID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = (TextView) findViewById(R.id.textView2);
        loginButton = (Button) findViewById(R.id.button3);
        eraseButton = (Button) findViewById(R.id.button);
        button0 = (Button) findViewById(R.id.button2);
        button1 = (Button) findViewById(R.id.button10);
        button2 = (Button) findViewById(R.id.button11);
        button3 = (Button) findViewById(R.id.button12);
        button4 = (Button) findViewById(R.id.button7);
        button5 = (Button) findViewById(R.id.button8);
        button6 = (Button) findViewById(R.id.button9);
        button7 = (Button) findViewById(R.id.button4);
        button8 = (Button) findViewById(R.id.button5);
        button9 = (Button) findViewById(R.id.button6);
        textView = (EditText)findViewById(R.id.editText);
        password = "";

        String userName = null;
        try {
            userName = ((SrsApplication) getApplication()).getActiveUserData().getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        name.setText(userName);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePasswordChar();
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasswordChar("9");
            }
        });
    }

    public void addPasswordChar(String digit){
        if (password.length() == 6) return;

        eraseButton.setBackgroundColor(Color.parseColor("#006c81"));
        password = password + digit;
        if (password.length() == 6) {
            loginButton.setBackgroundColor(Color.parseColor("#006c81"));
        }
        textView.setText(password);
    }

    public void removePasswordChar(){
        if (password.length() > 1) {
            password = password.substring(0, password.length() - 1);
        }
        else {
            password = "";
        }
        if (password.length() == 0) {
            eraseButton.setBackgroundColor(Color.parseColor("#7f7f7f"));
        }
        if (password.length() < 6) {
            loginButton.setBackgroundColor(Color.parseColor("#7f7f7f"));
        }
        textView.setText(password);
    }

    public void login(){
        if (password.length() == 6) {
            // TODO: Debug. Does not seem to get user number 1 if I type for example 001222, even though it should
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("userID", password);
            String threeFirst = password.substring(0, 3);
            int userId = Integer.parseInt(threeFirst);
            if (userId <= 100 && userId > 0) {
                readIntoApplication(userId);
            }
            startActivity(intent);
        }
    }

    private void readIntoApplication(int userId) {
        AssetManager am = getAssets();

        try {
            InputStream is = am.open("database.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            String json = new String(buffer);

            JSONObject obj = new JSONObject(json);
//            ((SrsApplication) getApplication()).setActiveUserData(obj);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(this, SelectUserActivity.class);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            return true;
        }
        return false;
    }
}
