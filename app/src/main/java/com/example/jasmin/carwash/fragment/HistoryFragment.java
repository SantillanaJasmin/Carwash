package com.example.jasmin.carwash.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.adapter.HistoryExpandableAdapter;
import com.example.jasmin.carwash.asynctask.GetHistoryAsyncTask;
import com.example.jasmin.carwash.asynctask.GetRatingAsyncTask;
import com.example.jasmin.carwash.model.HistoryChild;
import com.example.jasmin.carwash.model.HistoryParent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private ArrayList<HistoryParent> historyList;
    private HistoryExpandableAdapter historyAdapter;
    private RecyclerView rvHistory;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        //Instantiate the History Parent list where the history items will be stored
        historyList = new ArrayList<>();

        //Handler for the Recycler View
        rvHistory = (RecyclerView) v.findViewById(R.id.rvHistory);

        //Get booking history from the database and store the result to String 's'
        GetHistoryAsyncTask getHistoryAsyncTask = new GetHistoryAsyncTask(getActivity());
        try {
            String s = getHistoryAsyncTask.execute().get();
            populateHistoryRecyclerView(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Instantiate History Adapter and pass the History List
        historyAdapter = new HistoryExpandableAdapter(getActivity(), historyList);

        //Set Adapter of Recycler View
        rvHistory.setAdapter(historyAdapter);
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("History");
    }

    /*Function for parsing JSON Object returned by the Async Task*/
    public void populateHistoryRecyclerView(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);

            // Getting JSON Array 'bookings' node
            JSONArray historyArray = jsonObject.getJSONArray("bookings");

            //Iterate through the node
            for(int i = 0; i < historyArray.length(); i++) {
                //Instantiate variables
                int booking_id = 0;                 //booking id
                int control_number = 0;     //control number
                double control_price = 0;   //total
                String date = "";
                Date control_date = null;   //schedule

                //Get JSON Object from the JSON Array 'bookings'
                JSONObject history = historyArray.getJSONObject(i);

                try {
                    //Get booking id
                    booking_id = history.getInt("id");

                    //Get control number
                    control_number = history.getInt("control_no");

                    //Get date
                    date = history.getString("schedule");

                    //Parse String 'date' to Java Date Object
                    TimeZone utc = TimeZone.getTimeZone("UTC");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    sdf.setTimeZone(utc);
                    GregorianCalendar cal = new GregorianCalendar(utc);
                    cal.setTime(sdf.parse(date));
                    control_date = cal.getTime();

                    //Get total price
                    control_price = history.getDouble("total");
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                } finally {
                    //Instantiate variables
                    int rate = 0;
                    String comment = "";

                    GetRatingAsyncTask getRatingAsyncTask = new GetRatingAsyncTask();   //Get ratings and comments of a booking_id
                    try {
                        String r = getRatingAsyncTask.execute(booking_id).get();

                        JSONObject rating = new JSONObject(r);

                        if(!rating.isNull("rate") || !rating.isNull("comment")) {       //check if result string has rate and comment items
                            rate = rating.getInt("rate");
                            comment = rating.getString("comment");

                            historyList.add(new HistoryParent(booking_id, control_number, control_date, control_price, rate, Arrays.asList(new HistoryChild(comment))));
                        } else {                                                        //if none, set rate to 0 and comment to empty string
                            historyList.add(new HistoryParent(booking_id, control_number, control_date, control_price, 0, Arrays.asList(new HistoryChild(""))));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
