package com.example.jasmin.carwash.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.model.Car;

import java.util.ArrayList;

/**
 * Created by Jasmin on 2/8/2017.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private ArrayList<Car> carList;

    public CarAdapter(ArrayList<Car> list) {
        this.carList = list;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        Car car = carList.get(position);

        holder.tvNameField.setText(car.getName());
        holder.tvPlateField.setText("Plate: " + car.getPlate());
        holder.tvLocationField.setText(car.getLocation());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCar;
        TextView tvNameField, tvPlateField, tvLocationField;
        View container;

         public CarViewHolder(View itemView) {
            super(itemView);

             ivCar = (ImageView) itemView.findViewById(R.id.ivCar);
             tvNameField = (TextView) itemView.findViewById(R.id.tvNameField);
             tvPlateField = (TextView) itemView.findViewById(R.id.tvPlateField);
             tvLocationField = (TextView) itemView.findViewById(R.id.tvLocationField);

             container = itemView.findViewById(R.id.carContainer);
         }
    }
}
