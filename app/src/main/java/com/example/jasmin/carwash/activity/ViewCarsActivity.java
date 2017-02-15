package com.example.jasmin.carwash.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jasmin.carwash.model.Car;
import com.example.jasmin.carwash.adapter.CarAdapter;
import com.example.jasmin.carwash.R;

import java.util.ArrayList;

public class ViewCarsActivity extends AppCompatActivity {

    ArrayList<Car> carList;
    CarAdapter carAdapter;
    RecyclerView rvCars;

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

        carList = new ArrayList<>();
        carList.add(new Car("Honda Civic", "ABC 123"));
        carList.add(new Car("Toyota Fortuner", "DEF 456"));
        carList.add(new Car("Toyota Innova", "GHI 789"));
        carList.add(new Car("Mitsubishi Montero", "JKL 012"));
        carList.add(new Car("Mitsubishi Mirage", "MNO 345"));

        rvCars = (RecyclerView) findViewById(R.id.rvCars);
        carAdapter = new CarAdapter(carList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvCars.setLayoutManager(mLayoutManager);
        rvCars.setAdapter(carAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
