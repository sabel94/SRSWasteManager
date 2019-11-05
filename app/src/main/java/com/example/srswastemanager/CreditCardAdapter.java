package com.example.srswastemanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.CreditCardViewHolder> {
    private List<String> cards;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CreditCardViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView cardName;
        public CreditCardViewHolder(TextView cardName) {
            super(cardName);
            this.cardName = cardName;
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CreditCardAdapter(List<String> cards) {
        this.cards = cards;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CreditCardAdapter.CreditCardViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.credit_card_view, parent, false);
        // TODO
        return new CreditCardViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CreditCardViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cardName.setText(cards.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cards.size();
    }
}