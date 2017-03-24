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
 * Created by Jasmin on 3/20/2017.
 */
public class GetRatingAsyncTask extends AsyncTask<Integer, Void, String> {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    String sResult;

    @Override
    protected String doInBackground(Integer... params) {
        String url = "http://192.168.2.52:3004/carwash/rate/retrieve";

        JSONObject booking_id = new JSONObject();
        try {
            booking_id.put("booking_id", params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Instantiate client
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON, booking_id.toString());

        Request requestRating = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response;
        try {
            //the request will be executed and the response - a JSON Object -  will be stored to String 'sResult'
            response = client.newCall(requestRating).execute();
            sResult = response.body().string();

            return sResult;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
