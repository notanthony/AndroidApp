package com.example.servicenovigrad;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new UserData(in);
        }

        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

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

    // Parcelling part
      public UserData(Parcel in){
           this.name = in.readString();
           this.role =  in.readString();
       }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
       public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.role);
       }
}


