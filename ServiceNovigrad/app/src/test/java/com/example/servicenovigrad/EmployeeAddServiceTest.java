package com.example.servicenovigrad;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;




public class EmployeeAddServiceTest extends TestCase {

    ArrayList<Service>services = new ArrayList<>();
    FirebaseDatabase databaseServices = FirebaseDatabase.getInstance();
    DatabaseReference ref = databaseServices.getReference("services");



    protected void onCreate() {


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                services.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }



    @Test
    public void AddServiceTest(){
        Service service = services.get(0);
        EmployeeAddService Test = new EmployeeAddService();
        boolean result = Test.validateAddService(service);
        assertThat(result, is(true));

    }
}
