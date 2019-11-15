package com.example.srswastemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

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
//        addItem("Sep", "2019-10-31", "2019-10-31", "84.94");
//        addItem("Aug", "2019-09-30", "2019-09-30", "154.81");
//        addItem("Jul", "2019-08-31", "2019-08-31", "127.41");
//        addItem("Jun", "2019-07-31", "2019-07-31", "65.76");
//        addItem("May", "2019-06-30", "2019-06-30", "93.16");
//        addItem("Apr", "2019-05-31", "2019-05-31", "110.12");
//        addItem("Mar", "2019-04-30", "2019-04-30", "126.84");
//        addItem("Feb", "2019-03-31", "2019-03-31", "147.25");
//        addItem("Jan", "2019-02-30", "2019-02-30", "131.68");
        //---------------------------------------

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
        return years.stream().flatMap(year -> {
            try {
                JSONObject paymentsInYear = payments.getJSONObject(year);
                List<String> months = Lists.newArrayList(paymentsInYear.keys());
                return months.stream().map(month -> {
                    try {
                        JSONObject paymentInMonth = paymentsInYear.getJSONObject(month);
                        return new Payment(
                                StringUtils.capitalize(month.substring(0, 3)),
                                paymentInMonth.getString("payment_date"),
                                paymentInMonth.getString("payment_date"),
                                paymentInMonth.getDouble("total_amount")
                                );
                    } catch (JSONException e) {
                        return null;
                    }
                });
            } catch (JSONException e) {
                return null;
            }
        }).filter(Objects::nonNull).sorted((payment2, payment1) -> payment1.getDate().compareTo(payment2.getDate())).collect(Collectors.toList());
        // TODO: Sort in reverse order

    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItem(Payment payment) {
        if(payment.getMonth().equals("Jan") ||payment.getMonth().equals("Feb") || payment.getMonth().equals("Apr") || payment.getMonth().equals("Jun") || payment.getMonth().equals("Jul") || payment.getMonth().equals("Sep") || payment.getMonth().equals("Oct") || payment.getMonth().equals("Nov") || payment.getMonth().equals("Dec")) {
            listItems.add(Html.fromHtml(String.format(Locale.getDefault(), "%s\t\t\t\t\t\t%s\t\t\t\t\t\t%s\t\t\t\t\t\t<font color=#D2222D>-%.2f SEK</font>", payment.getMonth(), payment.getDueDate(), payment.getPaymentDate(), payment.getAmount())));
        }
        else if(payment.getMonth().equals("Mar") || payment.getMonth().equals("May") || payment.getMonth().equals("Aug")) {
            listItems.add(Html.fromHtml(String.format(Locale.getDefault(), "%s\t\t\t\t\t%s\t\t\t\t\t\t%s\t\t\t\t\t\t<font color=#D2222D>-%.2f SEK</font>", payment.getMonth(), payment.getDueDate(), payment.getPaymentDate(), payment.getAmount())));
        }
        adapter.notifyDataSetChanged();
    }
}
