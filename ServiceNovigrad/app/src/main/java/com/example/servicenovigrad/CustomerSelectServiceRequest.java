package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerSelectServiceRequest extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference databaseServiceRequests;
    ArrayList<Service> services;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button rate = (Button)findViewById(R.layout.rate);

        Button service = (Button)findViewById(R.layout.service);


        //need to make a new layout thats just the same as the one below
        setContentView(R.layout.activity_customer_select_service_request);
        final String branchID = getIntent().getStringExtra("branch");
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference(branchID+"/ServicesOffered");
        listView = (ListView) findViewById(R.id.listView);
        ((TextView) findViewById(R.id.dataType)).setText("Services Offered");
        ((TextView) findViewById(R.id.instructions)).setText("Tap on the service you want to request\nLong press to rate the branch");
        services = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent (CustomerSelectServiceRequest.this, CustomerCreateServiceRequest.class);
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
                services.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                ArrayAdapter<Service> serviceAdapter =
                        new ArrayAdapter<>(CustomerSelectServiceRequest.this, android.R.layout.simple_list_item_1 , services);
                listView.setAdapter(serviceAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rate: {
                findViewById(R.id.services);
                break;
            }
            case R.id.service: {

                break;
            }
        }
    }
}
