package com.example.servicenovigrad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Employee extends User implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button serviceRequestsButton = findViewById(R.id.serviceRequestsButton);
        serviceRequestsButton.setOnClickListener(this);
        Button employeeAddServicesButton = findViewById(R.id.employeeAddServicesButton);
        employeeAddServicesButton.setOnClickListener(this);
        Button employeeRemoveServicesButton = findViewById(R.id.employeeRemoveServicesButton);
        employeeRemoveServicesButton.setOnClickListener(this);
        Button editHoursButton = findViewById(R.id.editHoursButton);
        editHoursButton.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_employee;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.serviceRequestsButton: {
                Intent intent = new Intent(this, EmployeeServiceRequests.class);
                startActivity(intent);
                break;
            }
            case R.id.employeeAddServicesButton: {
                Intent intent = new Intent(this, EmployeeAddService.class);
                startActivity(intent);
                break;
            }
            case R.id.employeeRemoveServicesButton: {
                Intent intent = new Intent(this, EmployeeRemoveService.class);
                startActivity(intent);
                break;
            }
            case R.id.editHoursButton: {
                Intent intent = new Intent(this, EmployeeEditHours.class);
                startActivity(intent);
                break;
            }
        }
    }
}
