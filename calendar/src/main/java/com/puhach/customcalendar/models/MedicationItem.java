package com.puhach.customcalendar.models;


import java.util.Date;

/**
 * Created by user on 10/25/2016.
 */

public class MedicationItem {

    private Date createdAt;

    private Date closeddAt;

    private String state;

    private int stateInt;

    private Boolean completed;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCloseddAt() {
        return closeddAt;
    }

    public void setCloseddAt(Date closeddAt) {
        this.closeddAt = closeddAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStateInt() {
        return stateInt;
    }

    public void setStateInt(int stateInt) {
        this.stateInt = stateInt;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
