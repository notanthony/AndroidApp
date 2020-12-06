package com.example.servicenovigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomerCreateRequest extends AppCompatActivity {
    LinearLayout container;
    int[] formID;
    String name;
    List<String> forms;
    List<String> documents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_request);

        final String branchID = getIntent().getStringExtra("branch");
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference(branchID + "/ServiceRequests");
        final Service service;
        FirebaseDatabase.getInstance().getReference(branchID + "/ServicesOffered").child(getIntent().getStringExtra("service")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                service = dataSnapshot.getValue(Service.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        name = service.getServiceName();
    }


    public void clickSubmit() {


        String key = databaseServices.push().getKey();
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/ServiceRequests");
        databaseServices.child(key).setValue(new ServiceRequest(key, name, formEntries, documentReferences));
        userReference.child(key).setValue(branchID+"/ServiceRequests~"+key);

    }



}
