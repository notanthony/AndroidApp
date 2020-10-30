package com.example.servicenovigrad;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    List<UserData> UserDataList;
    DatabaseReference dataBaseUserData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserDataList = new ArrayList<>();
        dataBaseUserData = FirebaseDatabase.getInstance().getReference("UserData");
    }

    @Override
    protected void onStart() {
        super.onStart();
        dataBaseUserData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserDataList.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren() ){
                    UserData userData = postSnapshot.getValue(UserData.class);
                    UserDataList.add(userData);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public UserData searchUserEmail(String email){
        for(int i = 0; i<UserDataList.size(); i++){if(UserDataList.get(i).getEmail().equals(email)){return UserDataList.get(i);}}
        return null;
    }

    public UserData searchUserName(String name){
        for(int i = 0; i<UserDataList.size(); i++){if(UserDataList.get(i).getEmail().equals(name)){return UserDataList.get(i);}}
        return null;
    }


    public void disableuser(UserData user){
        String id = user.getId();
        String name = user.getName();
        String email = user.getEmail();
        String role = user.getRole();
        String accountStatus = "disabled";
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("UserData").child(id);
        UserData disabledUser = new UserData(id,name, role,email,"disabled");
        dr.setValue(disabledUser);
        Toast.makeText(getApplicationContext(), "User Account Disabled", Toast.LENGTH_LONG).show();
    }


    protected int getLayoutResourceId() {
        return R.layout.activity_admin;
    }
}


