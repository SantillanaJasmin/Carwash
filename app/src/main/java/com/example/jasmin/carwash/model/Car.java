package com.example.jasmin.carwash.model;

/**
 * Created by Jasmin on 2/8/2017.
 */
public class Car {

    private String model, type, plate, remarks;

    public Car(String model, String plate) {
        this.model = model;
        this.plate = plate;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
