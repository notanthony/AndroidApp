package com.example.servicenovigrad;

public class EmployeeData extends UserData {

    //hours and rating need to be implemented
    class Hours {
        private String[] defaultHours = new String[7];

    }

    private String phoneNumber;
    private Address address;
    private Hours workingHours;
    public EmployeeData (String name, UserRole role, String id, String email, String phoneNumber, Address address) {
        super(name, role, id, email);
        this.phoneNumber = phoneNumber;
        this.address = address;
        workingHours = new Hours();
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

    @Override
    public String toString() {
        return super.toString() +"\n"+ phoneNumber + "\n"+ address;
    }

}

