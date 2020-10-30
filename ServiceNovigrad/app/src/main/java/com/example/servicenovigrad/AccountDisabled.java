package com.example.servicenovigrad;

import android.content.Intent;
import android.os.Bundle;


import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountDisabled extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_disabled);
        Button logout = findViewById(R.id.dlogoutbutton);
        fAuth = FirebaseAuth.getInstance();

    }
    public void onLogoutButtonClicked(View view){
        fAuth.signOut();
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}