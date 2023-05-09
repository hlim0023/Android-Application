package com.example.Goalden;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Activity {

    //public static ArrayList<Activity> activitiesList = new ArrayList<>();

    public static ArrayList<Activity> activitiesForDate(LocalDate date){
        ArrayList<Activity> activities = new ArrayList<>();

        for(Activity activity: UserInfo.loggedUser.getActivities()){
            if(activity.getDate().equals(date)){
                activities.add(activity);
            }
        }

        return activities;
    }

    public static ArrayList<Activity> activitiesForDateAndTime(LocalDate date, LocalTime time){
        ArrayList<Activity> activities = new ArrayList<>();

        for(Activity activity: UserInfo.loggedUser.getActivities()){

            int eventHour = activity.getTime().getHour();
            int cellHour = time.getHour();

            if(activity.getDate().equals(date) && eventHour == cellHour){
                activities.add(activity);
            }
        }

        return activities;
    }

    private String activity_id;
    private String name;
    private LocalTime time;
    private LocalDate date;
    private String description;
    private ActivityType type;

    private boolean isComplete;
    public Activity(String activity_id, String name, LocalTime time, LocalDate date, ActivityType type, boolean isComplete) {
        this.activity_id = activity_id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.type = type;
        this.isComplete = isComplete;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
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
