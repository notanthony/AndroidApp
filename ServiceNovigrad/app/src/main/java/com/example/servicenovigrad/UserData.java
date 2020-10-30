//may use in database

package com.example.servicenovigrad;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData {

    private String id;
    private String name;
    private String role;
    private String email;
    private String accountStatus;



    // Constructor
    public UserData(String id, String name, String role, String email, String accountStatus){
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.accountStatus = accountStatus;
    }

    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getEmail(){return email;}
    public String getAccountStatus(){return accountStatus;}
    public String getId(){return id;}


}


