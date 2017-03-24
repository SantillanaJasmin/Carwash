package com.example.jasmin.carwash.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Jasmin on 3/6/2017.
 */
public class AddCarAsyncTask extends AsyncTask<String, Void, Void> {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

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
        String url = "http://192.168.2.52:3004/carwash/car/create";

        JSONArray array = new JSONArray();          //create json array for the car details
        JSONObject carDetails = new JSONObject();   //json object for car details
        JSONObject car = new JSONObject();          //json object containing "cars" and "user_id"
        try {
            carDetails.put("name", params[0]);
            carDetails.put("plate", params[1]);
            carDetails.put("location", params[2]);
            carDetails.put("lat", params[3]);
            carDetails.put("long", params[4]);

            array.put(0, carDetails);

            car.put("cars", array);
            car.put("user_id", 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            array.put(0, carDetails);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Instantiate client
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON, car.toString());

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
    }
}