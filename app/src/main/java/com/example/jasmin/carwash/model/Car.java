package com.example.jasmin.carwash.model;

/**
 * Created by Jasmin on 2/8/2017.
 */
public class Car {

    public static final String TABLE_NAME = "cars";
    public static final String COLUMN_ID = "_id";   //primary key
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ISDEFAULT = "isDefault";
    public static final String COLUMN_PLATE = "plate";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_LATI = "lati";
    public static final String COLUMN_LONGI = "longi";

    private int id;
    private boolean isDefault;
    private String name, plate, location;
    private double lati, longi;

    public Car(int id, boolean isDefault, String name, String plate, String location, double lati, double longi) {
        this.id = id;
        this.isDefault = isDefault;
        this.name = name;
        this.plate = plate;
        this.location = location;
        this.lati = lati;
        this.longi = longi;
    }

    public String getName() {
        return name;
    }

    public void setName(String model) {
        this.name = model;
    }

    public String getPlate() {
        return plate;
    }

    public String getLocation() {
        return location;
    }

    public double getLati() {
        return lati;
    }

    public double getLongi() {
        return longi;
    }

    public int getId() {
        return id;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
}
