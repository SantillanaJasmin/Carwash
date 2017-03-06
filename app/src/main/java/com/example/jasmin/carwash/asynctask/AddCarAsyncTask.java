package com.example.jasmin.carwash.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Jasmin on 3/6/2017.
 */
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
    }
}
