package com.example.jasmin.carwash.asynctask;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Jasmin on 3/24/2017.
 */
public class DeleteCarAsyncTask extends AsyncTask<Integer, Void, String> {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected String doInBackground(Integer... params) {
        String url = "http://192.168.2.52:3004/carwash/car/delete";

        int car_id = params[0];

        JSONObject car = new JSONObject();          //json object containing "cars" and "user_id"
        try {
            car.put("car_id", car_id);
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

        Response response;
        try {
            //the request will be executed and the response - a JSON Object -  will be stored to String 'sResult'
            response = client.newCall(request).execute();
            String sResult = response.body().string();

            return sResult;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
