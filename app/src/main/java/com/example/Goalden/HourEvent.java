package com.example.Goalden;

import java.time.LocalTime;
import java.util.ArrayList;

public class HourEvent {
    LocalTime time;
    ArrayList<Activity> activities;

    public HourEvent(LocalTime time, ArrayList<Activity> activities) {
        this.time = time;
        this.activities = activities;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }
}
