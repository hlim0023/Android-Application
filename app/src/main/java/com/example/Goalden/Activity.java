package com.example.Goalden;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Activity {

    public static ArrayList<Activity> activitiesList = new ArrayList<>();

    public static ArrayList<Activity> activitiesForDate(LocalDate date){
        ArrayList<Activity> activities = new ArrayList<>();

        for(Activity activity: activitiesList){
            if(activity.getDate().equals(date)){
                activities.add(activity);
            }
        }

        return activities;
    }

    public static ArrayList<Activity> activitiesForDateAndTime(LocalDate date, LocalTime time){
        ArrayList<Activity> activities = new ArrayList<>();

        for(Activity activity: activitiesList){

            int eventHour = activity.getTime().getHour();
            int cellHour = time.getHour();

            if(activity.getDate().equals(date) && eventHour == cellHour){
                activities.add(activity);
            }
        }

        return activities;
    }

    private String name;
    private LocalTime time;
    private LocalDate date;
    private String description;
    private ActivityType type;

    public Activity(String name, LocalTime time, LocalDate date) {
        this.name = name;
        this.time = time;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
}
