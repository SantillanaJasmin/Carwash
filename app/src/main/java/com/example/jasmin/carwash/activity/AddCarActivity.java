package com.example.jasmin.carwash.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.dbHelper.CarsDBHelper;
import com.example.jasmin.carwash.model.Car;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddCarActivity extends Activity {

    Button btnAdd, btnCancel;
    EditText etModel, etType, etPlate;

    String sResult;

    CarsDBHelper carsDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_car);

        etModel = (EditText) findViewById(R.id.etCarModel);
        etType = (EditText) findViewById(R.id.etCarType);
        etPlate = (EditText) findViewById(R.id.etCarPlate);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        carsDBHelper = new CarsDBHelper(getBaseContext());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vp) {
                String model = etModel.getText().toString();
                String type = etType.getText().toString();
                String plate = etPlate.getText().toString();

                new AddCarAsyncTask(getBaseContext()).execute(model, type, plate);

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
            String url = "http://192.168.2.212:8080/CarwashServer/AddCarServlet";

            //Instantiate client
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
                    .add("model", params[0])
                    .add("type", params[1])
                    .add("plate", params[2])
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
