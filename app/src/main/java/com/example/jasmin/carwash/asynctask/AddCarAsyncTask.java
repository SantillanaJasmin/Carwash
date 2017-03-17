package com.example.jasmin.carwash.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

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

        JsonNodeFactory nodeFactory = JsonNodeFactory.instance;

        ObjectNode node = nodeFactory.objectNode();

        ObjectNode child = nodeFactory.objectNode(); // the child

        child.put("name", params[0]);
        child.put("plate", params[1]);
        child.put("location", params[2]);
        child.put("lat", params[3]);
        child.put("long", params[4]);

        node.put("notification", child);
        node.put("user_id", 1);

        //Instantiate client
        OkHttpClient client = new OkHttpClient();

//        RequestBody requestBody = new FormBody.Builder()
//                .add("model", params[0])
//                .add("plate", params[1])
//                .add("location", params[2])
//                .add("lati", params[3])
//                .add("longi", params[4])
//                .build();
        RequestBody requestBody = RequestBody.create(JSON, node.toString());

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
