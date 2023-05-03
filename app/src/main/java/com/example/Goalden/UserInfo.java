package com.example.Goalden;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "results")
public class UserInfo {
    public static final String TABLE_NAME = "users";
    public static ArrayList<UserInfo> users = new ArrayList<>();

    public static UserInfo loggedUser = null;
    @PrimaryKey(autoGenerate = true)
    @NonNull

    @ColumnInfo(name = "userId")
    private String userId;

    @ColumnInfo(name = "userName")
    private String userName;

    @ColumnInfo(name = "userPassword")
    private String userPassword;

    @ColumnInfo(name = "activities")
    private ArrayList<Activity> activities;

    public UserInfo( String userId, String name, String password) {
        userName = name;
        userPassword = password;
        activities = new ArrayList<>();
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getUserName(){
        return userName;
    }

    @NonNull
    public String getUserPassword() {
        return userPassword;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public void update(String name, String userPassword, ArrayList<Activity> activities){
        this.userId = userId;
        this.userName = name;
        this.userPassword = userPassword;
        this.activities = activities;
    }

    public static UserInfo findUserInfoByName(String username){
        for(UserInfo userInfo: users){
            if(userInfo.userName.toLowerCase().equals(username.toLowerCase()))
                return userInfo;
        }
        return null;
    }

    @NonNull
    @Override
    public String toString() {
        return userName;
    }


}
