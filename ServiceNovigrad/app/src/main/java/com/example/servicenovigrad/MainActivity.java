package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button registerButton;
    Button loginButton;
    TextView mainTextView;

    private static FirebaseAuth admin = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerButton = findViewById(R.id.mregister);
        loginButton = findViewById(R.id.mlogin);
        admin.createUserWithEmailAndPassword("admin@admin.com","adminpass");

    }
    public void onClicLoginButton(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);


    }
    public void onClickRegisterButton(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);


    }



}