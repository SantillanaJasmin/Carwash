package com.example.jasmin.carwash.model;

import java.util.Date;

/**
 * Created by Jasmin on 1/16/2017.
 */
public class History {

    private int id, number;
    private double price;
    private int ratings;
    private Date date;
    private String comment;

    public History() {

    }

    public History(int number, Date date) {
        this.number = number;
        this.date = date;
    }

    public History(int number, Date date, double price) {
        this.number = number;
        this.date = date;
        this.price = price;
    }

    public History(int id, int trans_number, Date date, double trans_price, int ratings, String trans_comment) {
        this.id = id;
        this.number = trans_number;
        this.date = date;
        this.price = trans_price;
        this.comment = trans_comment;
        this.ratings = ratings;
    }

    public int getTrans_number() {
        return number;
    }

    public void setTrans_number(int trans_number) {
        this.number = trans_number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }
}
