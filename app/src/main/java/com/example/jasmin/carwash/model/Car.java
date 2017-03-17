package com.example.jasmin.carwash.model;

/**
 * Created by Jasmin on 2/8/2017.
 */
public class Car {

    private String name, type, plate, location;
    private double lati, longi;

    public Car() {

    }

    public Car(String model, String type, String plate) {
        this.name = model;
        this.type = type;
        this.plate = plate;
    }

    public Car(String model, String type, String plate, String location, double lati, double longi) {
        this.name = model;
        this.type = type;
        this.plate = plate;
        this.location = location;
        this.lati = lati;
        this.longi = longi;
    }

    public Car(String name, double lati, double longi) {
        this.name = name;
        this.lati = lati;
        this.longi = longi;
    }

    public String getName() {
        return name;
    }

    public void setName(String model) {
        this.name = model;
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
