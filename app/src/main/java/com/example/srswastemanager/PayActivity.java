package com.example.srswastemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        try {
            JSONObject paymentsJson = ((SrsApplication) getApplication()).getActiveUserData().getJSONObject("payments");
            List<Payment> payments = createPayments(paymentsJson);
            for (Payment payment : payments) {
                addItem(payment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    class Payment {
        private Date date;
        String month;
        String dueDate;
        String paymentDate;
        double amount;

        public Payment(String month, String dueDate, String paymentDate, double amount) {
            this.month = month;
            this.dueDate = dueDate;
            this.paymentDate = paymentDate;
            this.amount = amount;
            try {
                this.date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dueDate);
            } catch (ParseException e) {
                this.date = new Date();
            }
        }

        public String getMonth() {
            return month;
        }

        public String getDueDate() {
            return dueDate;
        }

        public String getPaymentDate() {
            return paymentDate;
        }

        public double getAmount() {
            return amount;
        }

        public Date getDate() {
            return date;
        }
    }

    private List<Payment> createPayments(JSONObject payments) {
        List<String> years = Lists.newArrayList(payments.keys());
        return years.stream()
                .flatMap(year -> {
                    try {
                        JSONArray paymentsInYear = payments.getJSONArray(year);
                        return IntStream.range(0, 11).mapToObj(i -> {
                            try {
                                JSONObject monthPayment = paymentsInYear.getJSONObject(i);
                                return new Payment(
                                        StringUtils.capitalize(StringUtils.capitalize(SrsApplication.months[i].substring(0, 3))),
                                        monthPayment.getString("payment_date"),
                                        monthPayment.getString("payment_date"),
                                        monthPayment.getDouble("total_amount"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                return null;
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(payment -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(payment.getDate());
                    return !(calendar.get(Calendar.YEAR) == 2019 && calendar.get(Calendar.MONTH) == 11);
                })
                .sorted((payment2, payment1) -> payment1.getDate().compareTo(payment2.getDate()))
                .collect(Collectors.toList());
    }

    public void addItem(Payment payment) {
        if(payment.getMonth().equals("Jan") || payment.getMonth().equals("Feb") || payment.getMonth().equals("Apr") || payment.getMonth().equals("Jun") || payment.getMonth().equals("Jul") || payment.getMonth().equals("Sep") || payment.getMonth().equals("Oct") || payment.getMonth().equals("Nov") || payment.getMonth().equals("Dec")) {
            listItems.add(Html.fromHtml(String.format(Locale.getDefault(), "%s\t\t\t\t\t\t%s\t\t\t\t\t\t%s\t\t\t\t\t\t<font color=#D2222D>-%.2f SEK</font>", payment.getMonth(), payment.getDueDate(), payment.getPaymentDate(), payment.getAmount())));
        }
        else if(payment.getMonth().equals("Mar") || payment.getMonth().equals("May") || payment.getMonth().equals("Aug")) {
            listItems.add(Html.fromHtml(String.format(Locale.getDefault(), "%s\t\t\t\t\t%s\t\t\t\t\t\t%s\t\t\t\t\t\t<font color=#D2222D>-%.2f SEK</font>", payment.getMonth(), payment.getDueDate(), payment.getPaymentDate(), payment.getAmount())));
        }
        adapter.notifyDataSetChanged();
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
