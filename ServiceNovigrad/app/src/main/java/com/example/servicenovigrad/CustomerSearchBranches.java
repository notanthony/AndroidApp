package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.TimeZone;

public class CustomerSearchBranches extends AppCompatActivity {
    DatabaseReference branchDataRef;
    ArrayList<EmployeeData> branches;
    ListView listViewBranches;
    ArrayAdapter<EmployeeData> branchAdapter;

    EditText openIncreasingSort;
    EditText openDecreasingSort;
    EditText closeIncreasingSort;
    EditText closeDecreasingSort;
    EditText citySort;
    EditText serviceSort;
    EditText postalCodeSort;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search_branches);

        openIncreasingSort = (EditText) findViewById(R.id.openIncreasing);
        openDecreasingSort = (EditText) findViewById(R.id.openDecreasing);
        closeIncreasingSort = (EditText) findViewById(R.id.closeIncreasing);
        closeDecreasingSort = (EditText) findViewById(R.id.closeDecreasing);
        citySort = (EditText) findViewById(R.id.city);
        serviceSort = (EditText) findViewById(R.id.service);
        postalCodeSort = (EditText) findViewById(R.id.postalCode);

        branchDataRef = FirebaseDatabase.getInstance().getReference("UserData");
        listViewBranches = (ListView) findViewById(R.id.listViewBranches);
        branches = new ArrayList<>();

        listViewBranches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (CustomerSearchBranches.this, CustomerSearchBranches.class);
                intent.putExtra( "branch" , branches.get(position).getId());
                startActivity(intent);
                finish();
            }
        });

        listViewBranches.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                EmployeeData branch = branches.get(i);
//                showUpdateDeleteDialog(service.getId(),service);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        branchDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                branches.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    EmployeeData branch = postSnapshot.getValue(EmployeeData.class);
                    if (branch.getRole() == UserData.UserRole.EMPLOYEE && branch.isActive()) {
                        branches.add(branch);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                listViewBranches.setAdapter(branchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.openIncreasing: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.openingHours(dayOfWeek(openIncreasingSort.getText().toString())));
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.openDecreasing: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.openingHours(dayOfWeek(openDecreasingSort.getText().toString())).reversed());
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.closeDecreasing: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.closingHours(dayOfWeek(closeDecreasingSort.getText().toString())).reversed());
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.closeIncreasing: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.closingHours(dayOfWeek(closeIncreasingSort.getText().toString())));
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.city: {
                ArrayList<EmployeeData> temp = new ArrayList<>();
                for(EmployeeData data : branches) {
                    if (data.getAddress().getCity().equals(citySort.getText().toString())) {
                        temp.add(data);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , temp);
                break;
            }
            case R.id.service: {
                ArrayList<EmployeeData> temp = new ArrayList<>();
                for(EmployeeData data : branches) {
                    if (data.getServiceNames().contains(serviceSort.getText().toString())) {
                        temp.add(data);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , temp);
                break;
            }
            case R.id.postalCode: {
                ArrayList<EmployeeData> temp = new ArrayList<>();
                for(EmployeeData data : branches) {
                    if (data.getAddress().getPostalCode().substring(0,3).equals(postalCodeSort.getText().toString())) {
                        temp.add(data);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , temp);
                break;
            }
        }
        //probably redunant to have the set adapter again but im not risking it
        listViewBranches.setAdapter(branchAdapter);
        listViewBranches.invalidateViews();
        listViewBranches.refreshDrawableState();
    }

    public int dayOfWeek (String day) {
        String weekday = day.toLowerCase();
        switch (weekday) {
            case "monday":
                return 0;
            case "tuesday":
                return 1;
            case "wednesday":
                return 2;
            case "thursday":
                return 3;
            case "friday":
                return 4;
            case "saturday":
                return 5;
            case "sunday":
                return 6;
        }
        return -1;
    }

}
