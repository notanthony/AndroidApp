package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceRequests extends AppCompatActivity {
    DatabaseReference databaseServiceRequests;
    List<ServiceRequests> serviceRequests;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_list);
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference("UserData");
        listView = (ListView) findViewById(R.id.listView);
        ((TextView) findViewById(R.id.dataType)).setText("Service Requests");
        ((TextView) findViewById(R.id.instructions)).setText("Tap on the service requests you want to view");
        serviceRequests = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeServiceRequests.this);
                final ServiceRequests service = serviceRequests.get(i);

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
                    ServiceRequests service = postSnapshot.getValue(ServiceRequests.class);
                    serviceRequests.add(service);
                }
                ArrayAdapter<ServiceRequests> serviceAdapter =
                        new ArrayAdapter<>(EmployeeServiceRequests.this, android.R.layout.simple_list_item_1 , serviceRequests);
                listView.setAdapter(serviceAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }
}
