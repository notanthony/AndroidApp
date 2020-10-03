package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    TextView title,title2,register;
    Button loginButton;
    ProgressBar progressBar;
    EditText email,password;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
        loginButton = findViewById(R.id.llogin);
        progressBar = findViewById(R.id.lprogressbar);


    }
}