package com.nghbui.model;

import java.util.Date;
import java.util.List;

public class ShortShift {
    private Date date;
    private int supermarketID;
    private int longShiftID;
    private String longShiftName;
    private int longShiftTime;
    private int shortShiftID;
    private int shortShiftTime;
    private int headCount;
    private List<Work> works;

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

    public String getLongShiftName() {
        return longShiftName;
    }

    public void setLongShiftName(String longShiftName) {
        this.longShiftName = longShiftName;
    }

    public int getLongShiftTime() {
        return longShiftTime;
    }

    public void setLongShiftTime(int longShiftTime) {
        this.longShiftTime = longShiftTime;
    }

    public int getShortShiftID() {
        return shortShiftID;
    }

    public void setShortShiftID(int shortShiftID) {
        this.shortShiftID = shortShiftID;
    }

    public int getShortShiftTime() {
        return shortShiftTime;
    }

    public void setShortShiftTime(int shortShiftTime) {
        this.shortShiftTime = shortShiftTime;
    }

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ShortShift) {
            ShortShift shortShift = (ShortShift) obj;
            if (this.getDate().equals(shortShift.getDate()) && this.getSupermarketID() == shortShift.getSupermarketID()
                    && this.getLongShiftID() == shortShift.getLongShiftID() && shortShift.getShortShiftID() == this.getShortShiftID())  {
                return true;
            }
            return false;
        }
        return false;
    }
}
