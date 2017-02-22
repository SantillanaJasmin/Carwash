package com.example.jasmin.carwash.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.jasmin.carwash.dbHelper.CarsDBHelper;
import com.example.jasmin.carwash.model.Car;
import com.example.jasmin.carwash.adapter.CarAdapter;
import com.example.jasmin.carwash.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * An Activity that displays all cars of a user
 */
public class ViewCarsActivity extends AppCompatActivity {

    static final int REQUEST_CAR = 0;

    ArrayList<Car> carList;
    CarAdapter carAdapter;
    RecyclerView rvCars;

    String sResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cars);

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

        carList = new ArrayList<>();

        //Handler for the Recycler View
        rvCars = (RecyclerView) findViewById(R.id.rvCars);

        GetCarsAsyncTask getCarsAsyncTask = new GetCarsAsyncTask(this);
        try {
            String s = getCarsAsyncTask.execute().get();
            populateCarsRecyclerView(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Instantiate Car Adapter and pass the Car list
        carAdapter = new CarAdapter(carList);

        //Set Adapter of Recycler View
        rvCars.setAdapter(carAdapter);
        rvCars.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        //Set click listener for the FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(), AddCarActivity.class);
                startActivityForResult(intent, REQUEST_CAR);
            }
        });
    }

    public void populateCarsRecyclerView(String s) {
        try {
            JSONArray carArray = new JSONArray(s);

            for(int i = 0; i < carArray.length(); i++) {
                JSONObject car = carArray.getJSONObject(i);

                String model = car.getString("model");
                String type = car.getString("type");
                String plate = car.getString("plate");

                carList.add(new Car(model, type, plate));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

//        GetCarsAsyncTask getCarsAsyncTask = new GetCarsAsyncTask(this);
//        try {
//            String s = getCarsAsyncTask.execute().get();
//            populateCarsRecyclerView(s);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        carAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAR) {
            if (resultCode == RESULT_OK) {
                carList.clear();
                GetCarsAsyncTask getCarsAsyncTask = new GetCarsAsyncTask(this);
                try {
                    String s = getCarsAsyncTask.execute().get();
                    populateCarsRecyclerView(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    carAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public class GetCarsAsyncTask extends AsyncTask<Void, Void, String> {

        Context mContext;
        ProgressDialog pdLoading;

        public GetCarsAsyncTask(Context context) {
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
            String url = "http://192.168.2.212:8080/CarwashServer/GetCarsServlet";

            //Instantiate client
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response;
            try {
                //the request will be executed and the response - a JSON Object -  will be stored to String 'sResult'
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
