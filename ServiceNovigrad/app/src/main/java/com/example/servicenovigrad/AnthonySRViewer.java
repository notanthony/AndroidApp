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

public class AnthonySRViewer extends AppCompatActivity {
    DatabaseReference branchDataRef;
    ArrayList<EmployeeData> branches;
    ListView listViewBranches;
    ArrayAdapter<EmployeeData> branchAdapter;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search_branches);

        branchDataRef = FirebaseDatabase.getInstance().getReference("UserData");
        listViewBranches = (ListView) findViewById(R.id.listViewBranches);
        branches = new ArrayList<>();

        listViewBranches.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (AnthonySRViewer.this, AnthonySSViewer.class);
                intent.putExtra( "branch" , branches.get(i).getId());
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
                        new ArrayAdapter<>(AnthonySRViewer.this, android.R.layout.simple_list_item_1 , branches);
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
                branches.sort(EmployeeData.openingHours(Integer.parseInt(daytext)));
                branchAdapter =
                        new ArrayAdapter<>(AnthonySRViewer.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.openDecreasing: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.openingHours(Integer.parseInt(daytext)).reversed());
                branchAdapter =
                        new ArrayAdapter<>(AnthonySRViewer.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.closeDecreasing: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.closingHours(Integer.parseInt(daytext)).reversed());
                branchAdapter =
                        new ArrayAdapter<>(AnthonySRViewer.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.openIncreasing: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.closingHours(Integer.parseInt(daytext)));
                branchAdapter =
                        new ArrayAdapter<>(AnthonySRViewer.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.city: {
                ArrayList<EmployeeData> temp = new ArrayList<>();
                for(EmployeeData data : branches) {
                    if (data.getAddress().getCity().equals(cityTextBox)) {
                        temp.add(data);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(AnthonySRViewer.this, android.R.layout.simple_list_item_1 , temp);
                break;
            }
            case R.id.service: {
                ArrayList<EmployeeData> temp = new ArrayList<>();
                for(EmployeeData data : branches) {
                    if (data.getServiceNames().contains(serviceTextBox)) {
                        temp.add(data);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(AnthonySRViewer.this, android.R.layout.simple_list_item_1 , temp);
                break;
            }
            case R.id.postalCode: {
                ArrayList<EmployeeData> temp = new ArrayList<>();
                for(EmployeeData data : branches) {
                    if (data.getAddress().getPostalCode().substring(0,3).equals(poTextBox)) {
                        temp.add(data);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(AnthonySRViewer.this, android.R.layout.simple_list_item_1 , temp);
                break;
            }
        }
        //probably redunant to have the set adapter again but im not risking it
        listViewBranches.setAdapter(branchAdapter);
        listViewBranches.invalidateViews();
        listViewBranches.refreshDrawableState();
    }

}
