package com.example.jasmin.carwash.model;

/**
 * Created by Jasmin on 2/8/2017.
 */
public class Car {

    public static final String TABLE_NAME = "cars";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_PLATE = "plate";

    private String model, type, plate, location;
    private double lati, longi;

    public Car() {

    }

    public Car(String model, double lati, double longi) {
        this.model = model;
        this.lati = lati;
        this.longi = longi;
    }

    public Car(String model, String type, String plate) {
        this.model = model;
        this.type = type;
        this.plate = plate;
    }

    public Car(String model, String type, String plate, String location, double lati, double longi) {
        this.model = model;
        this.type = type;
        this.plate = plate;
        this.location = location;
        this.lati = lati;
        this.longi = longi;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }
}
