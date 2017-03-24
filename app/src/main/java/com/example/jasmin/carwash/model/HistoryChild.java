package com.example.jasmin.carwash.model;

/**
 * Model for History comments that will be visible when arrow down button is pressed in HistoryParent
 */
public class HistoryChild {

    private String comment;

    public HistoryChild(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
