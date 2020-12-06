package com.example.servicenovigrad;

package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnthonySUViewer extends AppCompatActivity {
    DatabaseReference databaseServiceRequests;
    ArrayList<ServiceRequest> serviceRequests;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //need to make a new layout thats just the same as the one below
        setContentView(R.layout.activity_admin_disable_user);
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
                serviceRequests.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    String service = postSnapshot.getValue(String.class);
                    ServiceRequest sr;
                    FirebaseDatabase.getInstance().getReference(service.substring(0,service.indexOf('~'))).child(service.substring(service.indexOf('~')+1)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            sr = dataSnapshot.getValue(ServiceRequest.class);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    serviceRequests.add(sr);
                }
                ArrayAdapter<ServiceRequest> serviceAdapter =
                        new ArrayAdapter<>(AnthonySUViewer.this, android.R.layout.simple_list_item_1 , serviceRequests);
                listView.setAdapter(serviceAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }
}

