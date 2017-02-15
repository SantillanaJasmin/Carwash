package com.example.jasmin.carwash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.model.History;

import java.util.ArrayList;

/**
 * This will be used to handle each item in the Recycler View
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private ArrayList<History> historyList;
    private Context appContext;

    public HistoryAdapter(Context context, ArrayList<History> list) {
        this.appContext = context;
        this.historyList = list;
    }

    /*A class for setting handlers of the 'history_item' view*/
    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvControlNumberField, tvDateField, tvPriceField;
        View container;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            tvControlNumberField = (TextView) itemView.findViewById(R.id.tvControlNumField);
            tvDateField = (TextView) itemView.findViewById(R.id.tvDateField);
            tvPriceField = (TextView) itemView.findViewById(R.id.tvPriceField);

            container = itemView.findViewById(R.id.container);
        }
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout 'history_item' which will be view of each item in the Recycler View
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);

        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, int position) {
        //Get History item for the History List
        History history = historyList.get(position);

        //Set the Control Number of the 'history_item' view
        holder.tvControlNumberField.setText(String.valueOf(history.getTrans_number()));
        //Set the Date of the 'history_item' view
        holder.tvDateField.setText(String.valueOf(history.getDate()));
        //Set the Price of the 'history_item' view
        holder.tvPriceField.setText(String.valueOf(history.getPrice()) + " SAR");
    }


    /*Return the size of History list*/
    @Override
    public int getItemCount() {
        return historyList.size();
    }
}
