package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public abstract class User extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        Button logOut = findViewById(R.id.logout);
        TextView welcome = findViewById(R.id.welcomeUser);
        fAuth = FirebaseAuth.getInstance();
        String[] nameAndRole  = fAuth.getCurrentUser().getDisplayName().split(" | ");
        //temporary fix before setting up database
        String name = nameAndRole[0];
        String role = nameAndRole[1];
        welcome.setText("Welcome " + name + "!\nYou are logged in as \""+role+"\"");
    }

    protected abstract int getLayoutResourceId();

    public void onLogoutButtonClicked(View view){
        fAuth.signOut();
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
