package com.example.jasmin.carwash.addrating;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Jasmin on 3/22/2017.
 */
public class AddRatingAsyncTask extends AsyncTask<String, Void, Void> {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected Void doInBackground(String... params) {
        String url = "http://192.168.2.52:3004/carwash/rate";

        JSONObject rating = new JSONObject();
        try {
            rating.put("booking_id", Integer.parseInt(params[0]));
            rating.put("rate", Integer.parseInt(params[1]));
            rating.put("comment", params[2]);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Instantiate client
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON, rating.toString());

        Request requestRating = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            client.newCall(requestRating).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
