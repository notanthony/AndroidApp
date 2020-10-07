package com.example.servicenovigrad;
import android.os.Bundle;

public class Customer extends User {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_customer;
    }

}
