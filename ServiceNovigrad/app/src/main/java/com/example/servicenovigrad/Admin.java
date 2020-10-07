package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity {
    FirebaseAuth fAuth;
    Button logout;
    TextView userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        logout = findViewById(R.id.logout);
        fAuth = FirebaseAuth.getInstance();
        userType = (TextView) findViewById(R.id.welcomeUser);
        userType.setText("Welcome Admin");
    }
    public void onLogoutButtonClicked(View view){
        fAuth.signOut();
        Intent intent = new Intent (Admin.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}