package com.example.servicenovigrad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Customer extends User implements View.OnClickListener {
    DatabaseReference addService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button searchBranchesButton = findViewById(R.id.searchBranchesButton);
        searchBranchesButton.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_customer;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchBranchesButton: {
                Intent intent = new Intent(this, CustomerSearchBranches.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void addServiceRequest(String id, ServiceRequest aServiceRequest){
        addService = FirebaseDatabase.getInstance().getReference(id+"/ServiceRequests/Pending");
       FirebaseAuth fAuth = FirebaseAuth.getInstance();
       String customerId = fAuth.getCurrentUser().getUid();
       addService.child(customerId).setValue(aServiceRequest);

    }
}
