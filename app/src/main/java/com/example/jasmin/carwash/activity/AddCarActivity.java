package com.example.jasmin.carwash.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jasmin.carwash.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AddCarActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText etModel, etType, etPlate;
    TextView tvAddLocation;

    private static final int PLACE_PICKER_REQUEST = 1000;
    private GoogleApiClient mClient;

    String location;
    double lati, longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etModel = (EditText) findViewById(R.id.etCarModel);
        etType = (EditText) findViewById(R.id.etCarType);
        etPlate = (EditText) findViewById(R.id.etCarPlate);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        tvAddLocation = (TextView) findViewById(R.id.tvLocation);

        mClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vp) {
                String model = etModel.getText().toString();
                String type = etType.getText().toString();
                String plate = etPlate.getText().toString();

                new AddCarAsyncTask(getBaseContext()).execute(model, type, plate,  location, String.valueOf(lati), String.valueOf(longi));

                Intent result = new Intent();
                setResult(RESULT_OK, result);

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    Intent intent = builder.build(AddCarActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
    }

    @Override
    protected void onStop() {
        mClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(data, getBaseContext());

            lati = place.getLatLng().latitude;
            longi = place.getLatLng().longitude;
            location = String.format("%s", place.getAddress());

            tvAddLocation.setText(location);
//            Toast.makeText(AddCarActivity.this, "Location: " + location + "\tLat: " + String.valueOf(lati) + "\tLong: " + String.valueOf(longi), Toast.LENGTH_SHORT).show();
        } else {
            super.onActivityResult(requestCode, resultCode, data);

//            Toast.makeText(AddCarActivity.this, "Huhuhu besh!", Toast.LENGTH_SHORT).show();
        }
    }

    public class AddCarAsyncTask extends AsyncTask<String, Void, Void> {

        Context mContext;

        public AddCarAsyncTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            String url = "http://192.168.2.213:8080/CarwashServer/AddCarServlet";

            //Instantiate client
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
                    .add("model", params[0])
                    .add("type", params[1])
                    .add("plate", params[2])
                    .add("location", params[3])
                    .add("lati", params[4])
                    .add("longi", params[5])
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            try {
                //the request will be executed
                client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            finish();
        }
    }
}
