package com.example.servicenovigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


//since they want to view the images in the service requests it makes sense to have another viewer


public class EmployeeViewServiceRequest extends AppCompatActivity implements View.OnClickListener {
    private ServiceRequest serviceRequest;
    private DatabaseReference databaseServiceRequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view_service_requests);
        Button registerButton = findViewById(R.id.approve);
        registerButton.setOnClickListener(this);
        Button loginButton = findViewById(R.id.deny);
        loginButton.setOnClickListener(this);
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/ServiceRequests");
        databaseServiceRequests.child(getIntent().getStringExtra("request")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serviceRequest = dataSnapshot.getValue(ServiceRequest.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    //so the service requests can be added by the customer but we have not finished that yet.. so I am not sure how we would even go about this whole thing
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.approve: {
                serviceRequest.setApproved(true);
            }
            case R.id.deny: {
                serviceRequest.setApproved(false);
            }
        }
    }


}
