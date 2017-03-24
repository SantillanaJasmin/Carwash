package com.example.jasmin.carwash.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasmin.carwash.R;
import com.example.jasmin.carwash.asynctask.DeleteCarAsyncTask;
import com.example.jasmin.carwash.model.Car;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jasmin on 2/8/2017.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private Context context;
    private ArrayList<Car> carList;

    public CarAdapter(Context context, ArrayList<Car> list) {
        this.context = context;
        this.carList = list;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, final int position) {
        final Car car = carList.get(position);

        holder.tvNameField.setText(car.getName());
        holder.tvPlateField.setText("Plate: " + car.getPlate());
        holder.tvLocationField.setText(car.getLocation());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure you want to delete this car?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteCarAsyncTask deleteCarAsyncTask = new DeleteCarAsyncTask();
                        try {
                            String response = deleteCarAsyncTask.execute(car.getId()).get();

                            JSONObject object = new JSONObject(response);
                            String output = object.getString("type");
                            if (output.equals("2000")) {
                                Toast.makeText(context, car.getName() + " has been deleted.", Toast.LENGTH_LONG).show();

                                //Code snippet for removing items in a RecyclerView
                                carList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, getItemCount());
                                notifyDataSetChanged();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        Button btnDelete;
        ImageView ivCar;
        TextView tvNameField, tvPlateField, tvLocationField;
        View container;

        public CarViewHolder(View itemView) {
            super(itemView);

             ivCar = (ImageView) itemView.findViewById(R.id.ivCar);
             tvNameField = (TextView) itemView.findViewById(R.id.tvNameField);
             tvPlateField = (TextView) itemView.findViewById(R.id.tvPlateField);
             tvLocationField = (TextView) itemView.findViewById(R.id.tvLocationField);
             btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

             container = itemView.findViewById(R.id.carContainer);
        }
    }
}