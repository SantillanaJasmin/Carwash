package com.example.jasmin.carwash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jasmin.carwash.dbHelper.CarsDBHelper;
import com.example.jasmin.carwash.model.Car;
import com.example.jasmin.carwash.adapter.CarAdapter;
import com.example.jasmin.carwash.R;

import java.util.ArrayList;

/**
 * An Activity that displays the all cars of a user
 */
public class ViewCarsActivity extends AppCompatActivity {

    static final int REQUEST_CAR = 0;

    ArrayList<Car> carList;
    CarAdapter carAdapter;
    RecyclerView rvCars;

    CarsDBHelper carsDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cars);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Handler for the Recycler View
        rvCars = (RecyclerView) findViewById(R.id.rvCars);

        //Instantiate CarsDatabaseHelper
        carsDBHelper = new CarsDBHelper(getBaseContext());

        //Add cars to the database
        carsDBHelper.insertCar(new Car("Honda Civic", "ABC 123"));

        //Get all cars in the database and put it in an array list
        carList = carsDBHelper.getAllCars();

        //Instantiate Car Adapter and pass the Car list
        carAdapter = new CarAdapter(carList);

        //Set Adapter of Recycler View
        rvCars.setAdapter(carAdapter);
        rvCars.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        //Set click listener for the FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(), AddCarActivity.class);
                startActivityForResult(intent, REQUEST_CAR);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Clear Car List
        carList.clear();

        //Get all cars from the database
        carList.addAll(carsDBHelper.getAllCars());

        //To refresh the recycler view with the new Car list
        carAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAR) {
            if(resultCode == RESULT_OK) {
                //Clear Car List
                carList.clear();

                //Get all cars from the database
                carList.addAll(carsDBHelper.getAllCars());

                //To refresh the recycler view with the new Car list
                carAdapter.notifyDataSetChanged();
            }
        }
    }
}
