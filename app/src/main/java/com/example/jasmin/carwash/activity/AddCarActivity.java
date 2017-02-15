package com.example.jasmin.carwash.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.dbHelper.CarsDBHelper;
import com.example.jasmin.carwash.model.Car;

public class AddCarActivity extends Activity {

    Button btnAdd, btnCancel;
    EditText etModel, etPlate;

    CarsDBHelper carsDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_car);

        etModel = (EditText) findViewById(R.id.etCarModel);
        etPlate = (EditText) findViewById(R.id.etCarPlate);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        carsDBHelper = new CarsDBHelper(getBaseContext());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vp) {
                String model = etModel.getText().toString();
                String plate = etPlate.getText().toString();

                carsDBHelper.insertCar(new Car(model, plate));

                Intent result = new Intent();
                setResult(RESULT_OK, result);

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
