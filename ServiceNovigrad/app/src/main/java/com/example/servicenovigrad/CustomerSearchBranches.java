package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CustomerSearchBranches extends AppCompatActivity implements View.OnClickListener {

    TimePickerDialog picker;

    EditText branchAddress;
    EditText serviceType;
    EditText startTime;
    EditText endTime;
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);
    DatabaseReference branchDataRef;
    ArrayList<EmployeeData> branches;
    ListView listViewBranches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search_branches);
        calendar.setTimeZone(TimeZone.getDefault());

        branchDataRef = FirebaseDatabase.getInstance().getReference("UserData");
        startTime=(EditText) findViewById(R.id.searchStartTime);
        startTime.setInputType(InputType.TYPE_NULL);
        startTime.setOnClickListener(searchTimeClick);

        endTime=(EditText) findViewById(R.id.searchEndTime);
        endTime.setInputType(InputType.TYPE_NULL);
        endTime.setOnClickListener(searchTimeClick);
        listViewBranches = (ListView) findViewById(R.id.listViewBranches);

        branches = new ArrayList<>();

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
                BranchList branchAdapter = new BranchList(CustomerSearchBranches.this, branches);
                listViewBranches.setAdapter(branchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }

    private View.OnClickListener searchTimeClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            final Calendar cldr = Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int minutes = cldr.get(Calendar.MINUTE);
            // time picker dialog
            picker = new TimePickerDialog(CustomerSearchBranches.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                            String formattedTime = formatTime(sHour, sMinute); //time in AM/PM format
                            switch (v.getId()) {
                                case R.id.searchStartTime: {
                                    startTime.setText(formattedTime);
                                    break;
                                }
                                case R.id.searchEndTime: {
                                    endTime.setText(formattedTime);
                                    break;
                                }
                            }
                        }
                    }, hour, minutes, false);
            picker.show();
        }
    };

    public String formatTime(int hour, int min) {
        String timeStr = hour+":"+min;
        String formattedTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try{
            Date dt = sdf.parse(timeStr);
            //new format
            SimpleDateFormat sdfAmPm = new SimpleDateFormat("hh:mm aa");
            //formatting the given time to new format with AM/PM
//            System.out.println("Given time in AM/PM: "+sdfAmPm.format(dt));
            formattedTime = sdfAmPm.format(dt);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return formattedTime;
    }

    private void searchBranches () {

        String address = branchAddress.getText().toString();
        String services = serviceType.getText().toString();
        ArrayList<EmployeeData> searchResults = new ArrayList<>();
        for (EmployeeData b : branches) {
            if (b.getAddress().toString().contains(address)) {
                searchResults.add(b);
            }

//            if (EmployeeData.compareTime())
        }
        branches = searchResults;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchBranchesActivityButton: {
                String address = branchAddress.getText().toString();
                String services = serviceType.getText().toString();
                ArrayList<EmployeeData> searchResults = new ArrayList<>();
                for (EmployeeData b : branches) {
                    if (b.getAddress().getCity().equals(address)) {
                        searchResults.add(b);
                    }
                }

                BranchList branchAdapter = new BranchList(CustomerSearchBranches.this, searchResults);
                listViewBranches.setAdapter(branchAdapter);
                break;
            }
        }
    }
}