package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import java.util.List;

public class EmployeeEditHours extends AppCompatActivity {
    TimePickerDialog picker;

    EditText mondayStart;
    EditText mondayEnd;
    EditText tuesdayStart;
    EditText tuesdayEnd;
    EditText wednesdayStart;
    EditText wednesdayEnd;
    EditText thursdayStart;
    EditText thursdayEnd;
    EditText fridayStart;
    EditText fridayEnd;
    EditText saturdayStart;
    EditText saturdayEnd;
    EditText sundayStart;
    EditText sundayEnd;

    Button editHours;
    EmployeeHours branchHours;
    DatabaseReference databaseServices;

    ArrayList<String> openTimes = new ArrayList<String>();
    ArrayList<String> closeTimes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_hours);

        databaseServices = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/BranchHours");
        branchHours = new EmployeeHours();

        mondayStart=(EditText) findViewById(R.id.startTimeMonday);
        mondayStart.setInputType(InputType.TYPE_NULL);
        mondayStart.setOnClickListener(editTimeClick);

        mondayEnd=(EditText) findViewById(R.id.endTimeMonday);
        mondayEnd.setInputType(InputType.TYPE_NULL);
        mondayEnd.setOnClickListener(editTimeClick);

        tuesdayStart=(EditText) findViewById(R.id.startTimeTuesday);
        tuesdayStart.setInputType(InputType.TYPE_NULL);
        tuesdayStart.setOnClickListener(editTimeClick);

        tuesdayEnd=(EditText) findViewById(R.id.endTimeTuesday);
        tuesdayEnd.setInputType(InputType.TYPE_NULL);
        tuesdayEnd.setOnClickListener(editTimeClick);

        wednesdayStart=(EditText) findViewById(R.id.startTimeWednesday);
        wednesdayStart.setInputType(InputType.TYPE_NULL);
        wednesdayStart.setOnClickListener(editTimeClick);

        wednesdayEnd=(EditText) findViewById(R.id.endTimeWednesday);
        wednesdayEnd.setInputType(InputType.TYPE_NULL);
        wednesdayEnd.setOnClickListener(editTimeClick);

        thursdayStart=(EditText) findViewById(R.id.startTimeThursday);
        thursdayStart.setInputType(InputType.TYPE_NULL);
        thursdayStart.setOnClickListener(editTimeClick);

        thursdayEnd=(EditText) findViewById(R.id.endTimeThursday);
        thursdayEnd.setInputType(InputType.TYPE_NULL);
        thursdayEnd.setOnClickListener(editTimeClick);

        fridayStart=(EditText) findViewById(R.id.startTimeFriday);
        fridayStart.setInputType(InputType.TYPE_NULL);
        fridayStart.setOnClickListener(editTimeClick);

        fridayEnd=(EditText) findViewById(R.id.endTimeFriday);
        fridayEnd.setInputType(InputType.TYPE_NULL);
        fridayEnd.setOnClickListener(editTimeClick);

        saturdayStart=(EditText) findViewById(R.id.startTimeSaturday);
        saturdayStart.setInputType(InputType.TYPE_NULL);
        saturdayStart.setOnClickListener(editTimeClick);

        saturdayEnd=(EditText) findViewById(R.id.endTimeSaturday);
        saturdayEnd.setInputType(InputType.TYPE_NULL);
        saturdayEnd.setOnClickListener(editTimeClick);

        sundayStart=(EditText) findViewById(R.id.startTimeSunday);
        sundayStart.setInputType(InputType.TYPE_NULL);
        sundayStart.setOnClickListener(editTimeClick);

        sundayEnd=(EditText) findViewById(R.id.endTimeSunday);
        sundayEnd.setInputType(InputType.TYPE_NULL);
        sundayEnd.setOnClickListener(editTimeClick);

        editHours=(Button)findViewById(R.id.completeButton);
        editHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!compareTime(mondayStart.getText().toString(),mondayEnd.getText().toString())) {
                    mondayStart.setError("Start time must not be after end time. ");
                    return;
                }
                if(!compareTime(tuesdayStart.getText().toString(),tuesdayEnd.getText().toString())) {
                    tuesdayStart.setError("Start time must not be after end time. ");
                    return;
                }
                if(!compareTime(wednesdayStart.getText().toString(),wednesdayEnd.getText().toString())) {
                    wednesdayStart.setError("Start time must not be after end time. ");
                    return;
                }
                if(!compareTime(thursdayStart.getText().toString(),thursdayEnd.getText().toString())) {
                    thursdayStart.setError("Start time must not be after end time. ");
                    return;
                }
                if(!compareTime(fridayStart.getText().toString(),fridayEnd.getText().toString())) {
                    fridayStart.setError("Start time must not be after end time. ");
                    return;
                }
                if(!compareTime(saturdayStart.getText().toString(),saturdayEnd.getText().toString())) {
                    saturdayStart.setError("Start time must not be after end time. ");
                    return;
                }
                if(!compareTime(sundayStart.getText().toString(),sundayEnd.getText().toString())) {
                    sundayStart.setError("Start time must not be after end time. ");
                    return;
                }
                updateHours(openTimes,closeTimes);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                 branchHours = dataSnapShot.getValue(EmployeeHours.class);
                if (branchHours == null || branchHours.getClosing() == null) {
                    openTimes = new ArrayList<String>();
                    closeTimes = new ArrayList<String>();
                    for(int i=0; i<7; i++) {
                        openTimes.add("09:00 AM");
                        closeTimes.add("05:00 PM");
                    }
                    branchHours = new EmployeeHours(openTimes,closeTimes);
                } else {
                    openTimes = branchHours.getOpening();
//                    System.out.println("children: " + dataSnapShot.getChildrenCount());
//                    System.out.println("open: " +openTimes);
                    closeTimes = branchHours.getClosing();
                }

                mondayStart.setText(openTimes.get(0));
                mondayEnd.setText(closeTimes.get(0));
                tuesdayStart.setText(openTimes.get(1));
                tuesdayEnd.setText(closeTimes.get(1));
                wednesdayStart.setText(openTimes.get(2));
                wednesdayEnd.setText(closeTimes.get(2));
                thursdayStart.setText(openTimes.get(3));
                thursdayEnd.setText(closeTimes.get(3));
                fridayStart.setText(openTimes.get(4));
                fridayEnd.setText(closeTimes.get(4));
                saturdayStart.setText(openTimes.get(5));
                saturdayEnd.setText(closeTimes.get(5));
                sundayStart.setText(openTimes.get(6));
                sundayEnd.setText(closeTimes.get(6));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }

    private View.OnClickListener editTimeClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            final Calendar cldr = Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int minutes = cldr.get(Calendar.MINUTE);
            // time picker dialog
            picker = new TimePickerDialog(EmployeeEditHours.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                            String formattedTime = formatTime(sHour, sMinute); //time in AM/PM format
                            switch (v.getId()) {
                                case R.id.startTimeMonday: {
                                    mondayStart.setText(formattedTime);
                                    openTimes.set(0,formattedTime);
                                    break;
                                }
                                case R.id.endTimeMonday: {
                                    mondayEnd.setText(formattedTime);
                                    if(!compareTime(mondayStart.getText().toString(),formattedTime)) {
                                        mondayEnd.setError("Start time must not be after end time. ");
                                        return;
                                    }
                                    closeTimes.set(0,formattedTime);
                                    break;
                                }
                                case R.id.startTimeTuesday: {
                                    tuesdayStart.setText(formattedTime);
                                    openTimes.set(1,formattedTime);
                                    break;
                                }
                                case R.id.endTimeTuesday: {
                                    tuesdayEnd.setText(formattedTime);
                                    closeTimes.set(1,formattedTime);
                                    break;
                                }
                                case R.id.startTimeWednesday: {
                                    wednesdayStart.setText(formattedTime);
                                    openTimes.set(2,formattedTime);
                                    break;
                                }
                                case R.id.endTimeWednesday: {
                                    wednesdayEnd.setText(formattedTime);
                                    closeTimes.set(2,formattedTime);
                                    break;
                                }
                                case R.id.startTimeThursday: {
                                    thursdayStart.setText(formattedTime);
                                    openTimes.set(3,formattedTime);
                                    break;
                                }
                                case R.id.endTimeThursday: {
                                    thursdayEnd.setText(formattedTime);
                                    closeTimes.set(3,formattedTime);
                                    break;
                                }
                                case R.id.startTimeFriday: {
                                    fridayStart.setText(formattedTime);
                                    openTimes.set(4,formattedTime);
                                    break;
                                }
                                case R.id.endTimeFriday: {
                                    fridayEnd.setText(formattedTime);
                                    closeTimes.set(4,formattedTime);
                                    break;
                                }
                                case R.id.startTimeSaturday: {
                                    saturdayStart.setText(formattedTime);
                                    openTimes.set(5,formattedTime);
                                    break;
                                }
                                case R.id.endTimeSaturday: {
                                    saturdayEnd.setText(formattedTime);
                                    closeTimes.set(5,formattedTime);
                                    break;
                                }
                                case R.id.startTimeSunday: {
                                    sundayStart.setText(formattedTime);
                                    openTimes.set(6,formattedTime);
                                    break;
                                }
                                case R.id.endTimeSunday: {
                                    sundayEnd.setText(formattedTime);
                                    closeTimes.set(6,formattedTime);
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

    private void updateHours(ArrayList<String> opening, ArrayList<String> closing) {
        branchHours = new EmployeeHours(openTimes,closeTimes);
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/BranchHours");
        dR.setValue(branchHours);
        Toast.makeText(getApplicationContext(), "Hours Updated", Toast.LENGTH_LONG).show();
    }

    private boolean compareTime(String start, String end) {
        SimpleDateFormat parseTime = new SimpleDateFormat("hh:mm aa");
        try {
            Date startTime = parseTime.parse(start);
            Date endTime = parseTime.parse(end);

            if(startTime.before(endTime)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }

}