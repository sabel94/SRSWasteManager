package com.example.srswastemanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;

        public UserViewHolder(View view) {
            super(view);
            this.userName = view.findViewById(R.id.textView8);
        }

        public void setUserName(String userName) {
            this.userName.setText(userName);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserAdapter(List<User> users) {
        this.users = users;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_view, parent, false);
        UserViewHolder holder = new UserViewHolder(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = users.get(holder.getAdapterPosition()).getUserId();
                // TODO: Read user data to application
                JSONObject userData;
                try {
                    userData = SelectUserActivity.getAllUserData().getJSONObject(userId);
                    SrsApplication.getApplication().setActiveUserData(userData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                Context context = v.getContext();
                Intent intent = new Intent(context, BankIdActivity.class);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        User user = users.get(position);

        holder.setUserName(user.getUserName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return users.size();
    }
}