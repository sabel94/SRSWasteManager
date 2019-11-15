package com.example.srswastemanager;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectUserActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;

    public static JSONObject allUserData;

    public static JSONObject getAllUserData() {
        return allUserData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        List<User> users = readUsersFromFile();
        adapter = new UserAdapter(users);

        recyclerView.setAdapter(adapter);

        // TODO: Make each user clickable (clicking should trigger that user's data being read into the application)

    }

    private List<User> readUsersFromFile() {
        AssetManager am = getAssets();
        List<User> users = new ArrayList<>();
        try {
            InputStream is = am.open("database.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            String json = new String(buffer);

            JSONObject obj = new JSONObject(json);
            allUserData = obj;

            Iterator<String> allUserData = obj.keys();
            allUserData.forEachRemaining(userId -> {
                try {
                    JSONObject userObject = obj.getJSONObject(userId);
                    String userName = userObject.getString("name");
                    users.add(new User(userId, userName));
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            });
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return users;
    }
}