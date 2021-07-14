package com.nghbui.model;

import java.util.Date;

public class Task {
    private Date date;
    private int supermarketID;
    private int longShiftID;
    private int shortShiftID;
    private int headCountInShift;
    private int shortShiftTime;
    private int longShiftTime;
    private String nameWork;
    private int categoryWork;
    private int quantityHumansWorking;
    private int minutesFinishWork;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSupermarketID() {
        return supermarketID;
    }

    public void setSupermarketID(int supermarketID) {
        this.supermarketID = supermarketID;
    }

    public int getLongShiftID() {
        return longShiftID;
    }

    public void setLongShiftID(int longShiftID) {
        this.longShiftID = longShiftID;
    }

    public int getShortShiftID() {
        return shortShiftID;
    }

    public void setShortShiftID(int shortShiftID) {
        this.shortShiftID = shortShiftID;
    }

    public int getHeadCountInShift() {
        return headCountInShift;
    }

    public void setHeadCountInShift(int headCountInShift) {
        this.headCountInShift = headCountInShift;
    }

    public int getShortShiftTime() {
        return shortShiftTime;
    }

    public void setShortShiftTime(int shortShiftTime) {
        this.shortShiftTime = shortShiftTime;
    }

    public int getLongShiftTime() {
        return longShiftTime;
    }

    public void setLongShiftTime(int longShiftTime) {
        this.longShiftTime = longShiftTime;
    }

    public String getNameWork() {
        return nameWork;
    }

    public void setNameWork(String nameWork) {
        this.nameWork = nameWork;
    }

    public int getCategoryWork() {
        return categoryWork;
    }

    public void setCategoryWork(int categoryWork) {
        this.categoryWork = categoryWork;
    }

    public int getQuantityHumansWorking() {
        return quantityHumansWorking;
    }

    public void setQuantityHumansWorking(int quantityHumansWorking) {
        this.quantityHumansWorking = quantityHumansWorking;
    }

    public int getMinutesFinishWork() {
        return minutesFinishWork;
    }

    public void setMinutesFinishWork(int minutesFinishWork) {
        this.minutesFinishWork = minutesFinishWork;
    }

    @Override
    public String toString() {
        return "Task{" +
                "date=" + date +
                ", supermarketID=" + supermarketID +
                ", longShiftID=" + longShiftID +
                ", shortShiftID=" + shortShiftID +
                ", headCountInShift=" + headCountInShift +
                ", shortShiftTime=" + shortShiftTime +
                ", longShiftTime=" + longShiftTime +
                ", nameWork='" + nameWork + '\'' +
                ", categoryWork=" + categoryWork +
                ", quantityHumansWorking=" + quantityHumansWorking +
                ", minutesFinishWork=" + minutesFinishWork +
                '}';
    }
}
