package com.example.servicenovigrad;

import androidx.annotation.NonNull;
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
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class  AdminEditService extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPrice;
    EditText editTextDocuments;
    EditText editTextForms;
    ListView listViewServices;
    DatabaseReference databaseServices;

    List<Service> services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_service);
        databaseServices = FirebaseDatabase.getInstance().getReference("services");
//        editTextName = (EditText) findViewById(R.id.editTextName);
//        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
//        editTextForms = (EditText) findViewById(R.id.editTextForms);
//        editTextDocuments = (EditText) findViewById(R.id.editTextDocuments);
        listViewServices = (ListView) findViewById(R.id.listViewServices);

        services = new ArrayList<Service>();

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                showUpdateDeleteDialog(service.getId(),service);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                services.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                ServiceList servicesAdapter = new ServiceList(AdminEditService.this, services);
                listViewServices.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }

    private void showUpdateDeleteDialog(final String serviceId, Service s) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText editTextPrice = (EditText) dialogView.findViewById(R.id.editTextPrice);
        final EditText editTextForms = (EditText) dialogView.findViewById(R.id.editTextForms);
        final EditText editTextDocuments = (EditText) dialogView.findViewById(R.id.editTextDocuments);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);

        //auto-fill update dialog with existing service info
        String prevForms = "";
        for (int i = 0; i<s.getForms().size(); i++) {
            if (i == s.getDocs().size() -1) {
                prevForms += s.getForms().get(i);
            } else {
                prevForms += s.getForms().get(i) + ",";
            }
        }
        String prevDocs = "";
        for (int i = 0; i<s.getDocs().size(); i++) {
            if (i == s.getDocs().size() -1) {
                prevDocs += s.getDocs().get(i);
            } else {
                prevDocs += s.getDocs().get(i) + ",";
            }
        }
        editTextName.setText(s.getServiceName());
        editTextPrice.setText(String.valueOf(s.getPrice()));
        editTextForms.setText(prevForms);
        editTextDocuments.setText(prevDocs);

        dialogBuilder.setTitle("Service Name: " + s.getServiceName());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editTextName.getText().toString().trim())) {editTextName.setError("Service Name is Required. ");return;}
                if(TextUtils.isEmpty(editTextPrice.getText().toString().trim())) {editTextPrice.setError("Price is Required. ");return;}
                if(TextUtils.isEmpty(editTextForms.getText().toString().trim())) {editTextForms.setError("Please enter the required form(s) for this service or \"none\" if none are required");return;}
                if(TextUtils.isEmpty(editTextDocuments.getText().toString().trim())) {editTextDocuments.setError("Please enter the required document(s) for this service or \"none\" if none are required. ");return;}

                String name = editTextName.getText().toString().trim();
                double price = Double.parseDouble(String.valueOf(editTextPrice.getText().toString()));
                String form = editTextForms.getText().toString().trim();
                String doc = editTextDocuments.getText().toString().trim();
                //code for regex here
                List<String> forms = Arrays.asList(form.split("\\s,\\s"));
                List<String> documents = Arrays.asList(doc.split("\\s,\\s"));
                if (!TextUtils.isEmpty(name)) {
                    updateService(serviceId, name, price, forms, documents);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(serviceId);
                b.dismiss();
            }
        });
    }

    private void updateService(String id, String name, double price, List<String> forms, List<String> documents) {
        //getting the specified service reference

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        //updating service
        Service service = new Service(id,name,price,forms,documents);
        dR.setValue(service);

        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
    }

    private boolean deleteService(String id) {
        //getting the specified service reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        //removing service
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }
}