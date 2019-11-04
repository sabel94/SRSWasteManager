package com.example.srswastemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {

    ListView headers;
    ListView payments;
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<Spanned> listItems=new ArrayList<Spanned>();
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<Spanned> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Payments");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        payments = (ListView) findViewById(R.id.list);
        adapter=new ArrayAdapter<Spanned>(this, R.layout.list_item, listItems);
        payments.setAdapter(adapter);
        listItems.add(Html.fromHtml("<b>Month\t\t\t\tDue Date\t\t\t\t\t\t\t\tPayment Date\t\t\t\tAmount</b>"));





        //-----------Data Reading---------------
        addItems("Sep", "2019-10-31", "2019-10-31", "84.94");
        addItems("Aug", "2019-09-30", "2019-09-30", "154.81");
        addItems("Jul", "2019-08-31", "2019-08-31", "127.41");
        addItems("Jun", "2019-07-31", "2019-07-31", "65.76");
        addItems("May", "2019-06-30", "2019-06-30", "93.16");
        addItems("Apr", "2019-05-31", "2019-05-31", "110.12");
        addItems("Mar", "2019-04-30", "2019-04-30", "126.84");
        addItems("Feb", "2019-03-31", "2019-03-31", "147.25");
        addItems("Jan", "2019-02-30", "2019-02-30", "131.68");
        //---------------------------------------





    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(String month, String dueDate, String paymentDate, String amount) {
        if(month.equals("Jan") ||month.equals("Feb") || month.equals("Apr") || month.equals("Jun") || month.equals("Jul") || month.equals("Sep") || month.equals("Oct") || month.equals("Nov") || month.equals("Dec")) {
            listItems.add(Html.fromHtml(month+"\t\t\t\t\t\t"+dueDate+"\t\t\t\t\t\t"+paymentDate+"\t\t\t\t\t\t<font color=#D2222D>-"+amount+" SEK</font>"));
        }
        else if(month.equals("Mar") || month.equals("May") || month.equals("Aug")) {
            listItems.add(Html.fromHtml(month+"\t\t\t\t\t"+dueDate+"\t\t\t\t\t\t"+paymentDate+"\t\t\t\t\t\t<font color=#D2222D>-"+amount+" SEK</font>"));
        }
        adapter.notifyDataSetChanged();
    }
}
