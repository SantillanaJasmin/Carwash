package com.example.jasmin.carwash.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jasmin.carwash.model.History;
import com.example.jasmin.carwash.adapter.HistoryAdapter;
import com.example.jasmin.carwash.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ViewHistoryActivity extends AppCompatActivity {

    ArrayList<History> historyList;
    HistoryAdapter historyAdapter;
    RecyclerView rvHistory;

    String sResult ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        historyList = new ArrayList<>();
        rvHistory = (RecyclerView) findViewById(R.id.rvHistory);

        GetHistoryAsyncTask getHistoryAsyncTask = new GetHistoryAsyncTask(this);
        try {
            String s = getHistoryAsyncTask.execute().get();
            populateHistoryRecyclerView(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        historyAdapter = new HistoryAdapter(getBaseContext(), historyList);
        rvHistory.setAdapter(historyAdapter);
        rvHistory.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    public void populateHistoryRecyclerView(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);

            // Getting JSON Array node
            JSONArray historyArray = jsonObject.getJSONArray("bookings");

            for(int i = 0; i < historyArray.length(); i++) {
                int control_number = 0;
                double control_price = 0;
                String date = "";
                Date control_date = null;

                JSONObject history = historyArray.getJSONObject(i);

                try {
                    control_number = history.getInt("control_no");

                    date = history.getString("schedule");
                    TimeZone utc = TimeZone.getTimeZone("UTC");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    sdf.setTimeZone(utc);
                    GregorianCalendar cal = new GregorianCalendar(utc);
                    cal.setTime(sdf.parse(date));
                    control_date = cal.getTime();

                    control_price = history.getDouble("total");
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                } finally {
                    historyList.add(new History(control_number, control_date, control_price));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public class GetHistoryAsyncTask extends AsyncTask<Void, Void, String> {
        Context mContext;
        ProgressDialog pdLoading;

        public GetHistoryAsyncTask(Context context) {
            this.mContext = context;

            pdLoading = new ProgressDialog(mContext);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "http://192.168.2.52:3004/carwash/history/1";

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response;
            try {
                response = client.newCall(request).execute();
                sResult = response.body().string();
                return sResult;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pdLoading.dismiss();
        }
    }
}
