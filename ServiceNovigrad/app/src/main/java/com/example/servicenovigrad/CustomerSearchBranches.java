package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class CustomerSearchBranches extends AppCompatActivity {

    EditText branchAddress;
    EditText serviceType;
    EditText startTime;
    EditText endTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search_branches);
    }
}