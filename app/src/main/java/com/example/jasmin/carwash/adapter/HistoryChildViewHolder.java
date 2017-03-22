package com.example.jasmin.carwash.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.model.HistoryChild;

/**
 * Class for creating handlers of HistoryChild elements
 */
public class HistoryChildViewHolder extends ChildViewHolder {

    public TextView tvComment;

    public HistoryChildViewHolder(View itemView) {
        super(itemView);

        tvComment = (TextView) itemView.findViewById(R.id.tvComment);
    }

    public void bind(@NonNull HistoryChild historyChild) {
        tvComment.setText(historyChild.getComment());
    }
}
