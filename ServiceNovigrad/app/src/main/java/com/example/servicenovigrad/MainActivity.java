package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button mRegisterButton, mLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mRegisterButton = (Button)findViewById(R.id.register);
        registerButton.setOnClickListener(this);
        Button mLoginButton = (Button)findViewById(R.id.login);
        loginButton.setOnClickListener(this);
    }
    
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register: {
                Intent intent = new Intent(this,Register.class);
                startActivity(intent);
                break;
            }
            case R.id.login: {
                Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                break;
            }
        }
    }
}
