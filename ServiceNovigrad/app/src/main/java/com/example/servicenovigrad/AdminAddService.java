package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class AdminAddService extends AppCompatActivity {

    EditText editServiceName;
    EditText editServicePrice;
    EditText editServiceForms;
    EditText editServiceDocs;
    Button buttonAddService;

    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_service);
        editServiceName = findViewById(R.id.editServiceName);
        editServicePrice = findViewById(R.id.editServicePrice);
        editServiceForms = findViewById(R.id.editServiceForms);
        editServiceDocs = findViewById(R.id.editServiceDocs);
        //listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        buttonAddService = findViewById(R.id.addButton);
        databaseServices = FirebaseDatabase.getInstance().getReference("ServiceData");
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

    }
}
