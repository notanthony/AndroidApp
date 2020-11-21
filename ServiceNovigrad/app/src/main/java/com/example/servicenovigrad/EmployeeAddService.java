package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.List;

public class EmployeeAddService extends AppCompatActivity {
    DatabaseReference databaseServices;
    List<Service> services;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_service);
        databaseServices = FirebaseDatabase.getInstance().getReference("services");
        listView = (ListView) findViewById(R.id.listView);
        ((TextView) findViewById(R.id.dataType)).setText("Services");
        ((TextView) findViewById(R.id.instructions)).setText("Tap and hold on the services you want to add");
        services = new ArrayList<>();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeAddService.this);
                final Service service = services.get(i);
                builder.setCancelable(true);
                builder.setTitle("Add this service");
                builder.setMessage(services.get(i).toString());
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                databaseServices = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/ServicesOffered");
                                databaseServices.child(databaseServices.push().getKey()).setValue(service);
                                Toast.makeText(EmployeeAddService.this,"Service Added",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EmployeeAddService.this,"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }


    @Override
    protected void onStart() {

        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                services.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                ArrayAdapter<Service> serviceAdapter =
                        new ArrayAdapter<>(EmployeeAddService.this, android.R.layout.simple_list_item_1 , services);
                listView.setAdapter(serviceAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }

}
