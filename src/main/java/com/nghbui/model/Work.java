package com.nghbui.model;

import java.util.ArrayList;
import java.util.List;

public class Work {
    private String workName;
    private int categoryWork;
    private int quantityHumansWorking;
    private int minutesFinishWork;
    private List<Integer> workers = new ArrayList<>(); // people that assigned for work.

    public List<Integer> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Integer> workers) {
        this.workers = workers;
    }
    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
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
}
