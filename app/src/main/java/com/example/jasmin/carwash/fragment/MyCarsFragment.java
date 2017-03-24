package com.example.jasmin.carwash.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.activity.AddCarActivity;
import com.example.jasmin.carwash.adapter.CarAdapter;
import com.example.jasmin.carwash.asynctask.GetCarsAsyncTask;
import com.example.jasmin.carwash.model.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCarsFragment extends Fragment {

    public static final int REQUEST_CAR = 0;

    private ArrayList<Car> carList;
    private CarAdapter carAdapter;
    private RecyclerView rvCars;

    public MyCarsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_cars, container, false);

        carList = new ArrayList<>();

        //Handler for the Recycler View
        rvCars = (RecyclerView) v.findViewById(R.id.rvCars);

        GetCarsAsyncTask getCarsAsyncTask = new GetCarsAsyncTask(getActivity());
        try {
            String s = getCarsAsyncTask.execute().get();
            populateCarsRecyclerView(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Instantiate Car Adapter and pass the Car list
        carAdapter = new CarAdapter(getActivity(), carList);

        //Set Adapter of Recycler View
        rvCars.setAdapter(carAdapter);
        rvCars.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Set click listener for the FAB
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.addCar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddCarActivity.class);
                startActivityForResult(intent, REQUEST_CAR);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My Cars");
    }

    public void populateCarsRecyclerView(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);

            JSONArray carArray = jsonObject.getJSONArray("cars");

            for(int i = 0; i < carArray.length(); i++) {
                JSONObject car = carArray.getJSONObject(i);

                int id = car.getInt("id");
                String name = car.getString("name");
                String plate = car.getString("plate");
                String location = car.getString("location");
                double lati = Double.valueOf(car.getString("lat"));
                double longi = Double.valueOf(car.getString("long"));

                carList.add(new Car(id, name, plate, location, lati, longi));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAR) {
            if (resultCode == Activity.RESULT_OK) {
                carList.clear();
                GetCarsAsyncTask getCarsAsyncTask = new GetCarsAsyncTask(getActivity());
                try {
                    String s = getCarsAsyncTask.execute().get();
                    populateCarsRecyclerView(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    carAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}