package com.example.jasmin.carwash.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jasmin.carwash.model.Car;

import java.util.ArrayList;

/**
 * Created by Jasmin on 2/15/2017.
 */
public class CarsDBHelper extends SQLiteOpenHelper {

    public static final String SCHEMA = "notebook";

    public CarsDBHelper(Context context) {
        super(context, SCHEMA, null, 1);
    }

    /**
     * This is where the SCHEMA will be created
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // called once when SCHEMA has not been created.
        String noteTable = "CREATE TABLE " + Car.TABLE_NAME + " ( "
                + Car.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Car.COLUMN_MODEL + " STRING, "
                + Car.COLUMN_PLATE + " STRING );";
        db.execSQL(noteTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Function for inserting a car in the database
     */
    public void insertCar(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Car.COLUMN_MODEL, car.getModel());
        contentValues.put(Car.COLUMN_PLATE, car.getPlate());
        db.insert(Car.TABLE_NAME, null, contentValues);
        db.close();
    }

    /**
     * Function for getting all cars from the database
     */
    public ArrayList<Car> getAllCars(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Car.TABLE_NAME, null,
                null, null, null, null, null, null);

        ArrayList<Car> carArrayList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Car car = new Car();
                car.setModel(cursor.getString(cursor.getColumnIndex(Car.COLUMN_MODEL)));
                car.setPlate(cursor.getString(cursor.getColumnIndex(Car.COLUMN_PLATE)));

                carArrayList.add(car);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return carArrayList;
    }
}
