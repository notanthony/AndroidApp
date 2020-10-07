package com.example.servicenovigrad;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData {

    private String name;
    private String role;

    // Constructor
    public UserData(String name, String role){
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }
    
    public String getRole() {
        return role;
    }
    //getters not needed for part 1

}


