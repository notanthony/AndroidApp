package com.example.servicenovigrad;

import java.util.ArrayList;

public class EmployeeHours {
    public ArrayList<String> opening;
    public ArrayList<String> closing;

    public EmployeeHours() {}

    public EmployeeHours (ArrayList<String> open, ArrayList<String> close) {
        opening = open;
        closing = close;
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



}
