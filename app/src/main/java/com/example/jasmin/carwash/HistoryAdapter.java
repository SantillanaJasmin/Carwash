package com.example.jasmin.carwash;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView tvControlNumberLabel, tvControlNumberField, tvDateLabel, tvDateField;
        View container;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            tvControlNumberLabel = (TextView) itemView.findViewById(R.id.tvControlNumLabel);
            tvControlNumberField = (TextView) itemView.findViewById(R.id.tvControlNumField);
            tvDateLabel = (TextView) itemView.findViewById(R.id.tvDateLabel);
            tvDateField = (TextView) itemView.findViewById(R.id.tvDateField);

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

        holder.tvControlNumberField.setText(history.getTrans_number());
        holder.tvDateField.setText((CharSequence) history.getDate());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}
