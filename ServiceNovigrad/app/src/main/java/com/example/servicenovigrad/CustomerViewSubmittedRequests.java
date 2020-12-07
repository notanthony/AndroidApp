package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerViewSubmittedRequests extends AppCompatActivity{
    DatabaseReference databaseServiceRequests;
    ArrayList<ServiceRequest> serviceRequests;
    ArrayList<String> ref;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ref = new ArrayList<>();
        //need to make a new layout thats just the same as the one below
        setContentView(R.layout.activity_customer_view_submitted_requests);
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/ServiceRequests");
        listView = (ListView) findViewById(R.id.listView);
        ((TextView) findViewById(R.id.dataType)).setText("Service Requests");
        ((TextView) findViewById(R.id.instructions)).setText("View the status below");
    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseServiceRequests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                ref.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    String sr = postSnapshot.getValue(String.class);
                    ref.add(sr);
                }
                makeList();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });


    }

    void makeList() {
        for (int x = 0; x < ref.size(); x++) {
            String reference = ref.get(x);
            FirebaseDatabase.getInstance().getReference(reference.substring(0,reference.indexOf('~'))).child(reference.substring(reference.indexOf('~')+1)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ServiceRequest temp = dataSnapshot.getValue(ServiceRequest.class);
                    serviceRequests.add(temp);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        ArrayAdapter<ServiceRequest> serviceAdapter =
                new ArrayAdapter<>(CustomerViewSubmittedRequests.this, android.R.layout.simple_list_item_1 , serviceRequests);
        listView.setAdapter(serviceAdapter);
    }
}

