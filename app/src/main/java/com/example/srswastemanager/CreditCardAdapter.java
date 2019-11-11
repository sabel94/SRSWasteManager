package com.example.srswastemanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.CreditCardViewHolder> {
    private List<CreditCard> cards;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CreditCardViewHolder extends RecyclerView.ViewHolder {

        public TextView cardName;
        public TextView cardNumber;

        public CreditCardViewHolder(View itemView) {
            super(itemView);
            this.cardName = itemView.findViewById(R.id.textView6);
            this.cardNumber = itemView.findViewById(R.id.textView7);
        }

        public void setCardName(String cardName) {
            this.cardName.setText(cardName);
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber.setText(cardNumber);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CreditCardAdapter(List<CreditCard> cards) {
        this.cards = cards;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CreditCardAdapter.CreditCardViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.credit_card_view, parent, false);
        // TODO
        return new CreditCardViewHolder(linearLayout);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CreditCardViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        CreditCard card = cards.get(position);

        holder.setCardName(card.getCardName());
        holder.setCardNumber(card.getCardNumber());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cards.size();
    }
}