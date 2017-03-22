package com.example.jasmin.carwash.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.model.HistoryParent;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by Jasmin on 3/22/2017.
 */
public class HistoryParentViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    me.zhanghai.android.materialratingbar.MaterialRatingBar rb;
    ImageView ivExpand;
    TextView tvControlNumberField, tvDateField, tvPriceField;
    View container;

    public HistoryParentViewHolder(View itemView) {
        super(itemView);

        rb = (MaterialRatingBar) itemView.findViewById(R.id.rbRating);
        tvControlNumberField = (TextView) itemView.findViewById(R.id.tvControlNumField);
        tvDateField = (TextView) itemView.findViewById(R.id.tvDateField);
        tvPriceField = (TextView) itemView.findViewById(R.id.tvPriceField);
        ivExpand = (ImageView) itemView.findViewById(R.id.ivExpand);

        container = itemView.findViewById(R.id.container);

        rb.setEnabled(false);
    }

    public void bind(@NonNull HistoryParent historyParent) {
        tvControlNumberField.setText(String.valueOf(historyParent.getTrans_number()));
        tvDateField.setText(String.valueOf(historyParent.getDate()));
        tvPriceField.setText(String.valueOf(historyParent.getPrice()));

        rb.setRating(historyParent.getRatings());
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
                ivExpand.setRotation(ROTATED_POSITION);
            } else {
                ivExpand.setRotation(INITIAL_POSITION);
            }
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            RotateAnimation rotateAnimation;
            if (expanded) { // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else { // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
            ivExpand.startAnimation(rotateAnimation);
        }
    }
}
