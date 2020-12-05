package com.example.servicenovigrad;

import java.util.ArrayList;

public class EmployeeData extends UserData {

    //hours and rating need to be implemented
    private String phoneNumber;
    private Address address;
    public ArrayList<String> opening;
    public ArrayList<String> closing;

    public EmployeeData (String name, UserRole role, String id, String email, String phoneNumber, Address address, ArrayList<String> opening, ArrayList<String> closing) {
        super(name, role, id, email);
        this.phoneNumber = phoneNumber;
        this.address = address;
        if (opening == null) {
            for (int i = 0; i< 7; i++) {
                opening.add("09:00 AM");
            }
        } else {
            this.opening = opening;
        }
        if (closing == null) {
            for (int i = 0; i< 7; i++) {
                closing.add("09:00 AM");
            }
        } else {
            this.closing = closing;
        }
    }

    public EmployeeData() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Address getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(Address address){
        this.address = address;
    }
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOpening(ArrayList<String> open) {//day= 0 for monday ... day = 6 for sunday
        opening = open;
    }

    public void setClosing(ArrayList<String> close) {
        closing = close;
    }

    public ArrayList<String> getOpening() {
        return opening;
    }

    public ArrayList<String> getClosing() {
        return closing;
    }

    @Override
    public String toString() {
        return super.toString() +"\n"+ phoneNumber + "\n"+ address;
    }

}

