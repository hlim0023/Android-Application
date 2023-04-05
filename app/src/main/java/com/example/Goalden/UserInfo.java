package com.example.Goalden;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "results")
public class UserInfo {
    public static final String TABLE_NAME = "users";
    @PrimaryKey(autoGenerate = true)
    @NonNull

    @ColumnInfo(name = "username")
    private String userName;

    @ColumnInfo(name = "userpassword")
    private String userPassword;


    public UserInfo( String name, String password) {
        userName = name;
        userPassword = password;

    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
