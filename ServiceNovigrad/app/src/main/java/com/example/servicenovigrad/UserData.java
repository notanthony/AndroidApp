//may use in database

package com.example.servicenovigrad;

import java.util.ArrayList;

public class UserData {

    public enum UserRole {
        CUSTOMER, EMPLOYEE, ADMIN
    }

    public static String roleToString(UserRole role) {
        switch(role) {
            case CUSTOMER: {
                return "Customer";
            }
            case EMPLOYEE: {
                return "Employee";
            }
            case ADMIN: {
                return "Admin";
            }
        }
        return "";
    }

    private String name;
    private String email;
    private boolean active;
    private UserRole role;
    private String id;

    // Constructor

    public UserData(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserData(String name, UserRole role, String id, String email){
        this.name = name;
        this.role = role;
        this.id = id;
        this.email = email;
        active = true;
    }

    public String getName() {
        return name;
    }
    public UserRole getRole() {
        return role;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getId() {
        return id;
    }

    public void invertActive(){
        active = !active;
    }

    public boolean isActive() {
        return active;
    }

    public String toString() {
        return name+ (active ? "": " (disabled)") +"\n"+role.toString()+"\n"+email;
    }


}



