package com.example.jasmin.carwash.model;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.Date;
import java.util.List;

/**
 * Created by Jasmin on 3/22/2017.
 */
public class HistoryParent implements Parent<HistoryChild> {

    /* Create an instance variable for your list of children */
    private List<HistoryChild> mChildrenList;

    private int id, number;
    private double price;
    private int ratings;
    private Date date;
    private String comment;

    public HistoryParent(int id, int trans_number, Date date, double trans_price, int ratings, List<HistoryChild> list) {
        this.id = id;
        this.number = trans_number;
        this.date = date;
        this.price = trans_price;
        this.ratings = ratings;
        this.mChildrenList = list;
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

    @Override
    public List<HistoryChild> getChildList() {
        return mChildrenList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
