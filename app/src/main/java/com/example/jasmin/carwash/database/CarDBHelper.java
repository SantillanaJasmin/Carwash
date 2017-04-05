package com.example.jasmin.carwash.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jasmin.carwash.model.Car;

import java.util.ArrayList;

/**
 * Created by Jasmin on 4/3/2017.
 */
public class CarDBHelper extends SQLiteOpenHelper {

    public static final String SCHEMA = "carwash";

    public CarDBHelper(Context context) {
        super(context, SCHEMA, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +  Car.TABLE_NAME + " ( "
                + Car.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Car.COLUMN_NAME + " TEXT,"
                + Car.COLUMN_ISDEFAULT + " TEXT,"
                + Car.COLUMN_PLATE + " TEXT,"
                + Car.COLUMN_LOCATION + " TEXT,"
                + Car.COLUMN_LATI + " TEXT,"
                + Car.COLUMN_LONGI + " TEXT);";
        db.execSQL(sql);
    }

    //add default car
    public long addDefaultCar(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Car.COLUMN_ID, car.getId());
        cv.put(Car.COLUMN_NAME, car.getName());
        cv.put(Car.COLUMN_ISDEFAULT, String.valueOf(car.isDefault()));
        cv.put(Car.COLUMN_PLATE, car.getPlate());
        cv.put(Car.COLUMN_LOCATION, car.getLocation());
        cv.put(Car.COLUMN_LATI, String.valueOf(car.getLati()));
        cv.put(Car.COLUMN_LONGI, String.valueOf(car.getLongi()));

        long id = db.insert(Car.TABLE_NAME, null, cv);

        db.close();
        return id;
    }

    public int getCarCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Car.TABLE_NAME, null, null, null, null, null, null);

        return c.getCount();
    }

    public Car getCar() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Car.TABLE_NAME, null, null, null, null, null, null);

        Car car = null;
        if(c.moveToFirst()) {
            do {
                car = new Car(c.getInt(c.getColumnIndex(Car.COLUMN_ID)), Boolean.parseBoolean(c.getString(c.getColumnIndex(Car.COLUMN_ISDEFAULT))),
                        c.getString(c.getColumnIndex(Car.COLUMN_NAME)),c.getString(c.getColumnIndex(Car.COLUMN_PLATE)),
                        c.getString(c.getColumnIndex(Car.COLUMN_LOCATION)), Double.parseDouble(c.getString(c.getColumnIndex(Car.COLUMN_LATI))),
                        Double.parseDouble(c.getString(c.getColumnIndex(Car.COLUMN_LONGI))));
            } while(c.moveToNext());
        }

        return car;
    }

    //delete default car
    public int deleteDefaultCar(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        int num = db.delete(Car.TABLE_NAME, " " + Car.COLUMN_ID + " =? ", new String[]{String.valueOf(car.getId())});

        db.close();
        return num;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
