package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminAddService extends AppCompatActivity {

    EditText editServiceName;
    EditText editServicePrice;
    EditText editServiceForms;
    EditText editServiceDocs;
    Button buttonAddService;

    List<Service> services;

    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_service);

        editServiceName = (EditText) findViewById(R.id.editServiceName);
        editServicePrice = (EditText) findViewById(R.id.editServicePrice);
        editServiceForms = (EditText) findViewById(R.id.editServiceForms);
        editServiceDocs = (EditText) findViewById(R.id.editServiceDocs);

        //listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        buttonAddService = (Button) findViewById(R.id.addButton);

        databaseServices = FirebaseDatabase.getInstance().getReference("services");
        services = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });

    }


    private void addService() {

        //Toast.makeText(this, "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();

        //getting the values to save
        String name = editServiceName.getText().toString().trim();
        double price = Double.parseDouble(String.valueOf(editServicePrice.getText().toString()));
        String form = editServiceForms.getText().toString().trim();
        String doc = editServiceDocs.getText().toString().trim();


        //code for regex here
        Pattern requirementsPattern = Pattern.compile("\\s*+,+\\s*");
        String[] forms = requirementsPattern.split(form);
        String[] docs = requirementsPattern.split(doc);

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)){

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Service
            String id = databaseServices.push().getKey();

            //creating a Service Object
            Service service = new Service(id, name, price, forms, docs);

            //Saving the Service
            databaseServices.child(id).setValue(service);

            //setting edit text to blank again
            editServiceName.setText("");
            editServicePrice.setText("");
            editServiceForms.setText("");
            editServiceDocs.setText("");

            //displaying a success toast
            Toast.makeText(this, "Service added", Toast.LENGTH_LONG).show();
        }
        else{
            //if the value is not given diplaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
