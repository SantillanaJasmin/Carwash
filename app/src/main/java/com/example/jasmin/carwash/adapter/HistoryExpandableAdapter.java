package com.example.jasmin.carwash.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.model.HistoryChild;
import com.example.jasmin.carwash.model.HistoryParent;

import java.util.List;

/**
 * Adapter for Expandable Recycler View
 */
public class HistoryExpandableAdapter extends ExpandableRecyclerAdapter<HistoryParent, HistoryChild, HistoryParentViewHolder, HistoryChildViewHolder> {

    private Context context;
    private List<HistoryParent> parentList;

    public HistoryExpandableAdapter(Context context, @NonNull List<HistoryParent> parentList) {
        super(parentList);

        this.context = context;
        this.parentList = parentList;
    }

    @NonNull
    @Override
    public HistoryParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.history_item_parent, parentViewGroup, false);
        return new HistoryParentViewHolder(v);
    }

    @NonNull
    @Override
    public HistoryChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.history_item_child, childViewGroup, false);
        return new HistoryChildViewHolder(v);
    }

    @Override
    public void onBindParentViewHolder(@NonNull HistoryParentViewHolder parentViewHolder, int parentPosition, @NonNull HistoryParent parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull HistoryChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull HistoryChild child) {
        childViewHolder.bind(child);
    }
}
