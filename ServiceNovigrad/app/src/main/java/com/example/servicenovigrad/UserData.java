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
    private boolean accountActive;
    private UserRole role;

    // Constructor

    public UserData(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserData(String name, UserRole role){
        this.name = name;
        this.role = role;
        accountActive = true;
    }

    public String getName() {
        return name;
    }
    public UserRole getRole() {
        return role;
    }

    public boolean isActive(){
        return accountActive;
    }

    public void deactiveAccount() {
        accountActive = false;
    }

    public void activateAccount() {
        accountActive = true;
    }


}



