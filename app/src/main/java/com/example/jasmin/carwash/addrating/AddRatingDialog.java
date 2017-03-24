package com.example.jasmin.carwash.addrating;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.jasmin.carwash.R;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by Jasmin on 3/22/2017.
 */
public class AddRatingDialog extends DialogFragment {

    private View v;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_rating, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setTitle("Rate booking")
                .setView(v)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //get input of user
                        EditText comment = (EditText) v.findViewById(R.id.etComment);
                        me.zhanghai.android.materialratingbar.MaterialRatingBar rb = (MaterialRatingBar) v.findViewById(R.id.rbRating);
                        ((AddRatingActivity) getActivity()).onYesSelected(rb.getRating(), comment.getText().toString());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();  //if the user clicks on yes, exit dialog
                    }
                });
        return dialogBuilder.create();
    }
}
