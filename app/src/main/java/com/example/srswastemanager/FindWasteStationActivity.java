package com.example.srswastemanager;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FindWasteStationActivity extends AppCompatActivity {

    String availableStatus = "Available";
    String unavailableStatus = "Almost Full";
    String availableColor = "#238823";
    String unavailableColor = "#D2222D";
    String tab1 = "\t\t\t\t\t\t\t\t";
    String tab2 = "\t\t\t\t\t\t\t\t\t\t";
    ArrayList<String> statusCases = new ArrayList<String>();

    ArrayList<String> addresses;
    HashMap<String, ArrayList<Float>> addressToCoordinates;
    AutoCompleteTextView editText;
    Button findWasteStationButton;
    HashMap<String, ArrayList<Float>> wasteStationAddressToCoordinates;
    HashMap<Float, String> distanceToWasteStationAddress;
    ListView wasteStations;
    ArrayList<Spanned> listItems=new ArrayList<Spanned>();
    ArrayAdapter<Spanned> adapter;
    String previousSearch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Find Waste Stations");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_waste_station);





        //-----------Data Reading---------------
        wasteStationAddressToCoordinates = new HashMap<String, ArrayList<Float>>();
        ArrayList<Float> wasteStationcoordinates = new ArrayList<Float>();
        wasteStationcoordinates.add(59.359565f);
        wasteStationcoordinates.add(18.092085f);
        wasteStationAddressToCoordinates.put("Drevergatan 3", wasteStationcoordinates);

        wasteStationcoordinates = new ArrayList<Float>();
        wasteStationcoordinates.add(59.353273f);
        wasteStationcoordinates.add(18.088877f);
        wasteStationAddressToCoordinates.put("Lövängsgatan 2", wasteStationcoordinates);

        wasteStationcoordinates = new ArrayList<Float>();
        wasteStationcoordinates.add(59.358065f);
        wasteStationcoordinates.add(18.087720f);
        wasteStationAddressToCoordinates.put("Taxgatan 4", wasteStationcoordinates);

        wasteStationcoordinates = new ArrayList<Float>();
        wasteStationcoordinates.add(59.353476f);
        wasteStationcoordinates.add(18.094982f);
        wasteStationAddressToCoordinates.put("Untravägen 13", wasteStationcoordinates);

        wasteStationcoordinates = new ArrayList<Float>();
        wasteStationcoordinates.add(59.355735f);
        wasteStationcoordinates.add(18.099167f);
        wasteStationAddressToCoordinates.put("Artemisgatan 21", wasteStationcoordinates);

        wasteStationcoordinates = new ArrayList<Float>();
        wasteStationcoordinates.add(59.354299f);
        wasteStationcoordinates.add(18.099407f);
        wasteStationAddressToCoordinates.put("Skogvaktargatan 14", wasteStationcoordinates);
        //---------------------------------------


        String available = "<font color=\""+availableColor+"\">"+availableStatus+"</font>";
        String unavailable = "<font color=\""+unavailableColor+"\">"+unavailableStatus+"</font>";
        statusCases.add(available+tab2+available+tab2+available);
        statusCases.add(available+tab2+available+tab2+unavailable);
        statusCases.add(available+tab2+unavailable+tab1+available);
        statusCases.add(available+tab2+unavailable+tab1+unavailable);
        statusCases.add(unavailable+tab1+available+tab2+available);
        statusCases.add(unavailable+tab1+available+tab2+unavailable);
        statusCases.add(unavailable+tab1+unavailable+tab1+available);
        statusCases.add(unavailable+tab1+unavailable+tab1+unavailable);

        wasteStations = (ListView) findViewById(R.id.list);
        adapter=new ArrayAdapter<Spanned>(this, R.layout.list_item2, listItems);
        wasteStations.setAdapter(adapter);

        addressToCoordinates = new HashMap<String, ArrayList<Float>>();
        addresses = new ArrayList<String>();
        try {
            AssetManager am = getAssets();
            InputStream is = am.open("sthml_addresses.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(is));
            String[] nextLine;
            int counter = 0;
            while ((nextLine = reader.readNext()) != null) {
                if (counter > 0) {
                    String[] line = nextLine[0].split(";");
                    String address = line[3] + " " + line[2];
                    addresses.add(address);
                    ArrayList<Float> coordinates = new ArrayList<Float>();
                    coordinates.add(Float.parseFloat(line[1]));
                    coordinates.add(Float.parseFloat(line[0]));
                    addressToCoordinates.put(address, coordinates);
                }
                counter++;
            }
        }
        catch (IOException e) {}
        editText = findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addresses);
        editText.setAdapter(adapter);

        findWasteStationButton = (Button) findViewById(R.id.button15);
        findWasteStationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    editText.clearFocus();
                    findWasteStations(editText.getText().toString());
                }
                catch (Exception e) {
                    findWasteStations("");
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (listItems.size() > 0) {
                    listItems.clear();
                    updateAdapter();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void findWasteStations(String userAddress) {
        //Intent intent = new Intent(this, PayActivity.class);
        //startActivity(intent);
        //System.out.println(userAddress);
        listItems.clear();
        if (userAddress.length() == 0) {
            if (previousSearch != "") {
                userAddress = previousSearch;
            }
            else {
                userAddress = "Lindstedtsvägen 5";
                previousSearch = userAddress;
            }
        }
        else {
            previousSearch = userAddress;
        }

        distanceToWasteStationAddress = new HashMap<Float, String>();
        Location userLocation = new Location("");
        userLocation.setLatitude(addressToCoordinates.get(userAddress).get(0));
        userLocation.setLongitude(addressToCoordinates.get(userAddress).get(1));
        Location wasteStationLocation = new Location("");

        for (Map.Entry<String, ArrayList<Float>> entry : wasteStationAddressToCoordinates.entrySet()) {
            String key = entry.getKey();
            ArrayList<Float> val = entry.getValue();
            wasteStationLocation.setLatitude(val.get(0));
            wasteStationLocation.setLongitude(val.get(1));
            float distanceInMeters = userLocation.distanceTo(wasteStationLocation);
            float longestDistance = -1f;
            if (distanceToWasteStationAddress.size() >= 5) {
                for (Float distance : distanceToWasteStationAddress.keySet()) {
                    if (distance > longestDistance) {
                        longestDistance = distance;
                    }
                }
                if (distanceInMeters < longestDistance) {
                    distanceToWasteStationAddress.remove(longestDistance);
                    distanceToWasteStationAddress.put(distanceInMeters, key);
                }
            }
            else {
                distanceToWasteStationAddress.put(distanceInMeters, key);
            }
        }
        Float[] finalDistances = new Float[5];
        int i = 0;
        for (Float distance : distanceToWasteStationAddress.keySet()) {
            finalDistances[i] = distance;
            i++;
        }
        Arrays.sort(finalDistances);
        displayClosestWasteStations(finalDistances);
    }

    public void displayClosestWasteStations(Float[] finalDistances) {
        //listItems.add("Address\t\t\t\tHousehold Waste\t\t\t\t\t\t\t\tPlastic Packaging\t\t\t\tNewspapers\t\tDistance");
        listItems.add(Html.fromHtml(""));
        for (Float distance : finalDistances) {
            String address = distanceToWasteStationAddress.get(distance);
            int distanceRounded = Math.round(distance);
            addItems(address, distanceRounded);
        }
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(String address, int distance) {
        String distanceStr = "" + distance;
        if (distanceStr.length() > 3) {
            distanceStr = distanceStr.substring(0, 1) + " " + distanceStr.substring(1);
        }
        String item= "<b>"+address+"<br>"+distanceStr+" m</b><br><br>";
        item = item +"Household Waste\t\t\tPlastic Packaging\t\t\tNewspapers\n";
        item = item + statusCases.get(new Random().nextInt(statusCases.size()));
        listItems.add(Html.fromHtml(item));
        adapter.notifyDataSetChanged();
    }

    void updateAdapter() {
        adapter.notifyDataSetChanged();
    }
}
