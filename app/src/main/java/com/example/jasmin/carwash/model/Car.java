package com.example.jasmin.carwash.model;

/**
 * Created by Jasmin on 2/8/2017.
 */
public class Car {

    private int id;
    private String name, plate, location;
    private double lati, longi;

    public Car(int id, String name, String plate, String location, double lati, double longi) {
        this.id = id;
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
}
