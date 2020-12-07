package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    Button rate;
    EditText rating;
    EditText comment;
    String branchID;
    EmployeeData employee;
    DatabaseReference employeeRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //need to make a new layout thats just the same as the one below
        setContentView(R.layout.activity_customer_select_service_request);

        rate = (Button)findViewById(R.id.rate);
        rating = (EditText)findViewById(R.id.rating);
        comment = (EditText)findViewById(R.id.comment);
        branchID = getIntent().getStringExtra("branch");
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference(branchID+"/ServicesOffered");
        listView = (ListView) findViewById(R.id.listView);
        ((TextView) findViewById(R.id.dataType)).setText("Services Offered");
        ((TextView) findViewById(R.id.instructions)).setText("Tap on the service you want to request or rate the branch");
        services = new ArrayList<>();

        employeeRef= FirebaseDatabase.getInstance().getReference("UserData").child(branchID);
        employeeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employee = dataSnapshot.getValue(EmployeeData.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                String ratingNumber = rating.getText().toString().trim();
                if (ratingNumber == null) {
                    rating.setError("Enter a rating from 1-5");
                    return;
                }
                int n = 0;
                try {
                    n = Integer.parseInt(ratingNumber);
                } catch (NumberFormatException e) {
                    rating.setError("Enter a rating from 1-5");
                    return;
                }
                if (n > 5 || n < 1) {
                    rating.setError("Enter a rating from 1-5");
                    return;
                }
                String commentStr = comment.getText().toString().trim();
                if (commentStr == null) {
                    commentStr = "";
                }
                employee.inputRating(commentStr, n);
                break;
            }
        }
    }
}
