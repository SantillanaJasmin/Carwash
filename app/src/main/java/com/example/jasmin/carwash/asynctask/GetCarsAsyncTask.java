package com.example.jasmin.carwash.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jasmin on 3/6/2017.
 *
 * A class for fetching the car items from the database
 */
public class GetCarsAsyncTask extends AsyncTask<Void, Void, String> {

    Context mContext;
    ProgressDialog pdLoading;

    String sResult;

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

    //Fetching the car items from the database. OkHttpClient is used for faster retrieving of data
    @Override
    protected String doInBackground(Void... params) {
        String url = "http://192.168.2.214:8080/CarwashServer/GetCarsServlet";

        //Instantiate client
        OkHttpClient client = new OkHttpClient();

        //Request to server
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
