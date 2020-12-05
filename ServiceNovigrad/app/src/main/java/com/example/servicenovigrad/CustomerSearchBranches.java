package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CustomerSearchBranches extends AppCompatActivity {

    TimePickerDialog picker;

    EditText branchAddress;
    EditText serviceType;
    EditText startTime;
    EditText endTime;

    DatabaseReference databaseServices;
    ArrayList<EmployeeData> branchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search_branches);

        startTime=(EditText) findViewById(R.id.searchStartTime);
        startTime.setInputType(InputType.TYPE_NULL);
        startTime.setOnClickListener(searchTimeClick);

        endTime=(EditText) findViewById(R.id.searchEndTime);
        endTime.setInputType(InputType.TYPE_NULL);
        endTime.setOnClickListener(searchTimeClick);
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
    }

}