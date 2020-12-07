package com.example.servicenovigrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerCreateServiceRequest extends AppCompatActivity {
    LinearLayout container;
    int[] formID;
    String name;
    List<String> forms;
    List<String> documents;
    Service service;

    DatabaseReference databaseServiceRequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //okay actually make a new layout for this one
        setContentView(R.layout.activity_admin_disable_user);

        final String branchID = getIntent().getStringExtra("branch");
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference(branchID+"/ServiceRequests");
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

        //put these in titles
        name = service.getServiceName();
        service.getPrice();

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

         */


        //use ACTION_OPEN_DOCUMENT or whatever
        //make a bunch of document buttons see above for how to do it

/**
        String key = databaseServices.push().getKey();

        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid()+"/ServiceRequests");

        databaseServices.child(key).setValue(new ServiceRequest(key, name, formEntries, documentReferences));
        userReference.child(key).setValue(branchID+"/ServiceRequests~"+key);
*/
    }



}
