package com.example.servicenovigrad;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomerCreateServiceRequest extends AppCompatActivity {
    LinearLayout container;
    int[] formID;
    String name;
    List<String> forms;
    List<String> documents;
    Service service;
    double price;

    TextView priceField;
    TextView nameField;
    String branchID;
    DatabaseReference databaseServiceRequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_service_request);

        branchID = getIntent().getStringExtra("branch");
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference(branchID+"/ServiceRequests");
        /*
        FirebaseDatabase.getInstance().getReference(branchID+"/ServicesOffered").child(getIntent().getStringExtra("service")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                service = dataSnapshot.getValue(Service.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        container = (LinearLayout)findViewById(R.id.linearLayoutForms);
        priceField = (TextView) findViewById(R.id.serviceRequestPrice);
        nameField = (TextView) findViewById(R.id.serviceRequestTitle);


        //put these in titles
        name = service.getServiceName();
        price = service.getPrice();

        nameField.setText(name);
        priceField.setText(Double.toString(price));

        forms = service.getForms();
        documents = service.getDocs();
        formID = new int[forms.size()];
        for (int x = 0; x < forms.size(); x++) {
            EditText et = new EditText(this);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(p);
            et.setText("Enter: " + forms.get(x));
            int id = View.generateViewId();
            et.setId(id);
            formID[x] = id;
            container.addView(et);
        }

         */

    }

    public void clickSubmit() {
/*
        List<String> formEntries = new ArrayList<>(forms.size());
        for (int i = 0; i < forms.size(); i++) {
            final View row = container.getChildAt(i);
            EditText textOut = (EditText)row.findViewById(formID[i]);
            String entry = textOut.getText().toString().trim();
            if(entry == null) {
                //someone put in an error message
                return;
            }
            formEntries.add(entry);
        }

        ArrayList<String> documentReferences = new ArrayList<>();


 */

        EditText dummy = (EditText) findViewById(R.id.dummyField);

        String key = databaseServiceRequests.push().getKey();
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/ServiceRequests");
        databaseServiceRequests.child(key).setValue(new ServiceRequest(key, dummy.getText().toString()));
        userReference.child(key).setValue(branchID+"/ServiceRequests~"+key);

    }



}
