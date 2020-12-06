package com.example.servicenovigrad;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import java.util.ArrayList;


public class ServiceRequest implements Parcelable {
    //once we do the customer class we will put more implementation here
    //we are currently unsure on how we would take the image input...
    //for now all we need is a approval and denial for each requests
    private boolean approved;
    //primatives cant be null so we need to see if the service was even checked
    private boolean checked;
    ArrayList<String> form;
    String filePath;


    public ServiceRequest(ArrayList<String> form, String filePath) {
        this.form = form;
        this.filePath=filePath;

    }

    public boolean isApproved() {
        return approved;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setApproved(boolean approve) {
        approved = approve;
        checked = true;
    }

    public String toString() {
        return "This service has " + ((isChecked()) ? (isApproved() ? "been approved" : "not been approved"):"not been checked") ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private ServiceRequest(Parcel in) {
        boolean[] arr = new boolean[2];
        in.readBooleanArray(arr);
        approved = arr[0];
        checked = arr[1];
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBooleanArray(new boolean[]{approved, checked});
    }

    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Creator<ServiceRequest> CREATOR
            = new Creator<ServiceRequest>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public ServiceRequest createFromParcel(Parcel in) {
            return new ServiceRequest(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public ServiceRequest[] newArray(int size) {
            return new ServiceRequest[size];
        }
    };

}
