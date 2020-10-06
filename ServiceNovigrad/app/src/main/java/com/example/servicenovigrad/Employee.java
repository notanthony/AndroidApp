package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Employee extends AppCompatActivity {
    TextView welcome;
    FirebaseAuth fAuth;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        logout = findViewById(R.id.elogout);
        welcome = findViewById(R.id.etitle2);
        String customerName = getIntent().getExtras().getString("name","Customer");
        welcome.setText("Welcome Employee " + customerName);
        fAuth = FirebaseAuth.getInstance();
    }
    public void onLogoutButtonClicked(View view){
        fAuth.signOut();
        Intent intent = new Intent (Employee.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}