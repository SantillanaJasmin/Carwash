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
 * Created by Jasmin on 2/1/2017.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private ArrayList<History> historyList;
    private Context appContext;
    private OnItemClickListener mOnDetailClickListener, mOnDeleteClickListener;

    public HistoryAdapter(Context context, ArrayList<History> list) {
        this.appContext = context;
        this.historyList = list;
    }

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

//    public void setmOnDetailClickListener(OnItemClickListener m) {
//        this.mOnDetailClickListener = m;
//    }
//
//    public void setmOnDeleteClickListener(OnItemClickListener m) {
//        this.mOnDeleteClickListener = m;
//    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(v);
    }

    public interface OnItemClickListener {
        public void onItemClick(int id);
    }

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, int position) {
        History history = historyList.get(position);

        holder.tvControlNumberField.setText(String.valueOf(history.getTrans_number()));
        holder.tvDateField.setText(String.valueOf(history.getDate()));
        holder.tvPriceField.setText(String.valueOf(history.getPrice()) + " SAR");
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}
