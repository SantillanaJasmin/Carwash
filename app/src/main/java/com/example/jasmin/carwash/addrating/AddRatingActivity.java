package com.example.jasmin.carwash.addrating;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AddRatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddRating = (Button) findViewById(R.id.btnAddRating);

        View.OnClickListener openDialog = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a dialog
                DialogFragment df = new AddRatingDialog();
                df.show(getFragmentManager(), "AddRatingFrag");
            }
        };
        btnAddRating.setOnClickListener(openDialog);
    }

    public void onYesSelected(float rate, String comment) {
        int booking_id = 1;                 //just change this part

        new AddRatingAsyncTask().execute(String.valueOf(booking_id), String.valueOf(((int) rate)), comment);
    }
}
