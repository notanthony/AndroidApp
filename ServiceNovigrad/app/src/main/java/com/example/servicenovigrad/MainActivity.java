package com.example.servicenovigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements OnClickListener {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button registerButton = (Button)findViewById(R.id.mregister);
        registerButton.setOnClickListener(this);
        Button loginButton = (Button)findViewById(R.id.mlogin);
        loginButton.setOnClickListener(this);
    }

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.mregister: {
			Intent intent = new Intent(this, Register.class);
			startActivity(intent);
			break;
		}
		case R.id.mlogin: {
			Intent intent = new Intent(this, Login.class);
			startActivity(intent);
			break;
		    }
	    }
    }
}
