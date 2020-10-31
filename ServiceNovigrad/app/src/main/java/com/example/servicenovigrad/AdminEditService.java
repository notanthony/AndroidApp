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
import java.util.List;

public class AdminEditService extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPrice;
    EditText editTextDocuments;
    ListView listViewServices;
    DatabaseReference databaseServices;

    List<Service> services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_service);
        databaseServices = FirebaseDatabase.getInstance().getReference("services");
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextDocuments = (EditText) findViewById(R.id.editTextDocuments);
        listViewServices = (ListView) findViewById(R.id.listViewServices);

        services = new ArrayList<Service>();

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                showUpdateDeleteDialog(service.getName());
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

    private void showUpdateDeleteDialog(final String serviceName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText editTextPrice  = (EditText) dialogView.findViewById(R.id.editTextPrice);
        final EditText editTextForms = (EditText) dialogView.findViewById(R.id.editTextForms);
        final EditText editTextDocuments  = (EditText) dialogView.findViewById(R.id.editTextDocuments);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);

        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                double price = Double.parseDouble(String.valueOf(editTextPrice.getText().toString()));
                String[] forms = editTextForms.getText().toString().split(",");
                String[] documents = editTextDocuments.getText().toString().split(",");
                if (!TextUtils.isEmpty(name)) {
                    updateService(name, price, forms, documents);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(serviceName);
                b.dismiss();
            }
        });
    }

    private void updateService(String name, double price, String[] forms, String[] documents) {
        //getting the specified service reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(name);
        //updating service
        Service service = new Service(name,price,forms,documents);
        dR.setValue(service);

        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
    }

    private boolean deleteService(String name) {
        //getting the specified service reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(name);
        //removing service
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }
}