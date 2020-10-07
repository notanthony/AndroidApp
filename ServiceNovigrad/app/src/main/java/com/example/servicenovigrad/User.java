package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class User extends AppCompatActivity {
    FirebaseAuth fAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button logOut = findViewById(R.id.logout);
        TextView welcome = findViewById(R.id.welcomeUser);
        fAuth = FirebaseAuth.getInstance();
        String[] nameAndRole  = FirebaseAuth.getCurrentUser().getDisplayName().split(" | ");
        //temporary fix before setting up database
        String name = nameAndRole[0];
        String role = nameAndRole[1];
        
        welcome.setText("Welcome " + name + "!\nYou are logged in as \""+role+"\"");
    }
    public void onLogoutButtonClicked(View view){
        fAuth.signOut();
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
