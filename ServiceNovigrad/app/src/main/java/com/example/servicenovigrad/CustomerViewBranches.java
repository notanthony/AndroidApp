package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class CustomerViewBranches extends AppCompatActivity {
    DatabaseReference databaseServiceRequests;
    ArrayList<Service> services;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customer_services_display);
        final String branchID = getIntent().getStringExtra("branch");
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference(branchID+"/ServicesOffered");
        listView = (ListView) findViewById(R.id.listView);
        ((TextView) findViewById(R.id.dataType)).setText("Services Offered");
        ((TextView) findViewById(R.id.instructions)).setText("Tap on the service you want to request");
        serviceRequests = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent (CustomerViewBranches.this, CustomerCreateRequest.class);
                intent.putExtra( "service", services.get(i).getId());
                intent.putExtra( "branch", branchID);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    protected void onStart() {

        super.onStart();
        databaseServiceRequests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                serviceRequests.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    Service service = postSnapshot.getValue(Service.class);
                    if (!service.isChecked()) {
                        services.add(service);
                    }
                }
                ArrayAdapter<ServiceRequest> serviceAdapter =
                        new ArrayAdapter<>(CustomerViewBranches.this, android.R.layout.simple_list_item_1 , services);
                listView.setAdapter(serviceAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }
}