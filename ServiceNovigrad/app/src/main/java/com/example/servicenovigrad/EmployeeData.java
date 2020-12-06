package com.example.servicenovigrad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class EmployeeData extends UserData {

    //hours and rating need to be implemented
    private String phoneNumber;
    private Address address;
    private ArrayList<String> opening;
    private ArrayList<String> closing;

    private ArrayList<String> comments;
    private ArrayList<Float> branchRatings;
    private float avgBranchRating;
    private float totalBranchRatingSum;


    private ArrayList<String> serviceNames = new ArrayList<String>();

    public EmployeeData (String name, UserRole role, String id, String email, String phoneNumber, Address address, ArrayList<String> opening, ArrayList<String> closing, float avgBranchRating, ArrayList<Float> branchRatings, ArrayList<String> comments) {
        super(name, role, id, email);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.name=name;
        this.comments=new ArrayList<String>();

        totalBranchRatingSum=0; //total number of ratings for the branch
//        for(int i = 0; i < this.branchRatings.size(); i++){
//            totalBranchRatingSum += branchRatings.get(i);
//        }

//        this.avgBranchRating=(totalBranchRatingSum/this.branchRatings.size()); //new average branch rating
        this.avgBranchRating = 0;

        this.branchRatings=new ArrayList<Float>();
//        this.avgBranchRating=avgBranchRating; //only really need this if we ever want to display or search the branches average rating
        this.avgBranchRating = 0;


        if (opening == null || closing == null) {
            opening = new ArrayList<>(7);
            closing = new ArrayList<>(7);
            for (int i = 0; i< 7; i++) {
                opening.add("09:00 AM");
            }
            for (int i = 0; i< 7; i++) {
                closing.add("05:00 PM");
            }
        } else {
            this.opening = opening;
            this.closing = closing;
        }

    }

    public EmployeeData() {

    }
    public void setAvgBranchRating(float avgBranchRating){this.avgBranchRating=avgBranchRating;}
    public float getAvgBranchRating(){return avgBranchRating;}

    public void setBranchRatings(ArrayList<String> custComment) {
        comments = custComment;
    }
    public ArrayList<Float> getBranchRatings() {
        return branchRatings;
    }

    public void setComments(ArrayList<String> custComment) {
        comments = custComment;
    }
    public ArrayList<String> getComments() {
        return comments;
    }

    public void setBranchName(String name){
        this.name = name;
    }
    public String getBranchName() {
        return this.name;
    }

    public void setUserRole(UserRole role){
        this.role = role;
    }
    public UserRole getUserRole() {
        return this.role;
    }

    public ArrayList<String> getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(ArrayList<String> serviceNames) {
        this.serviceNames = serviceNames;
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

    static Comparator<EmployeeData> openingHours(final int day) {
        return new Comparator<EmployeeData>() {
            @Override
            public int compare(EmployeeData branch1, EmployeeData branch2) {
                String open1 = branch1.getOpening().get(day);
                String open2 = branch2.getOpening().get(day);
                return compareTime(open1,open2);
            }
        };
    }

    static Comparator<EmployeeData> closingHours(final int day) {
        return new Comparator<EmployeeData>() {
            @Override
            public int compare(EmployeeData branch1, EmployeeData branch2) {
                String close1 = branch1.getClosing().get(day);
                String close2 = branch2.getClosing().get(day);
                return compareTime(close1,close2);
            }
        };
    }

    public static int compareTime(String first, String second) {
        SimpleDateFormat parseTime = new SimpleDateFormat("hh:mm aa");
        try {
            Date firstTime = parseTime.parse(first);
            Date secondTime = parseTime.parse(second);

            if(firstTime.before(secondTime)) {
                return -1;
            }
            if (secondTime.before(firstTime)) {
                return 1;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() +"\n"+ phoneNumber + "\n"+ address;
    }

}

