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

        holder.tvCarModelField.setText(car.getModel());
        holder.tvPlateNumField.setText(car.getPlate());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCar;
        TextView tvCarModelField, tvPlateNumField;
        View container;

         public CarViewHolder(View itemView) {
            super(itemView);

             ivCar = (ImageView) itemView.findViewById(R.id.ivCar);
             tvCarModelField = (TextView) itemView.findViewById(R.id.tvCarModelField);
             tvPlateNumField = (TextView) itemView.findViewById(R.id.tvPlateNumField);

             container = itemView.findViewById(R.id.carContainer);
         }
    }
}
