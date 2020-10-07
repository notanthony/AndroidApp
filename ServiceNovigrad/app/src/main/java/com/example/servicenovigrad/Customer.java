package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Customer extends AppCompatActivity {
    Button searchBranch;
    Button selectService;
    Button logOut;
    TextView welcome;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
//        searchBranch = findViewById(R.id.csearchbutton);
//        selectService = findViewById(R.id.cselectservicebutton);
        logOut = findViewById(R.id.logout);
        welcome = findViewById(R.id.welcomeUser);
        String customerName = getIntent().getExtras().getString("name","Customer");
        welcome.setText("Welcome " + customerName + "!\nYou are logged in as \"Customer\"");

        fAuth = FirebaseAuth.getInstance();



    }
    public void onLogoutButtonClicked(View view){
        fAuth.signOut();
        Intent intent = new Intent (Customer.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}