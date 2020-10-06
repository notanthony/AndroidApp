package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Employee extends AppCompatActivity {

    Button searchBranch;
    Button selectService;
    Button logOut;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        logOut = findViewById(R.id.elogoutbutton);
        fAuth = FirebaseAuth.getInstance();
    }
    public void onLogoutButtonClicked(View view){
        fAuth.signOut();
        Intent intent = new Intent (Employee.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}