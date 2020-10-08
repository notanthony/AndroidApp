package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText email, password;
    private FirebaseAuth fAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
        Button loginButton = findViewById(R.id.llogin);
        progressBar = findViewById(R.id.lprogressbar);
        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");

    }

    public void onLoginButtonClicked(View view) {
        String inputEmail = email.getText().toString().trim();
        String inputPassword = password.getText().toString().trim();
        if (TextUtils.isEmpty(inputEmail)) {
            email.setError("Email is Required. ");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){email.setError("Valid Email Required. ");return;}
        if (TextUtils.isEmpty(inputPassword)) {
            password.setError("Password is Required. ");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        fAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {


                if (task.isSuccessful())
                {
                    Toast.makeText(Login.this,"User Logged In",Toast.LENGTH_LONG).show();
                    String displayName = fAuth.getCurrentUser().getDisplayName();
                    String[] roleAndName = displayName.split("[|]");
                    String role = roleAndName[0];

                    if(role.equals("Customer")) {
                        Intent intent = new Intent(Login.this, Customer.class);
                        startActivity(intent);
                        finish();
                    }
                    if(role.equals("Employee")) {
                        Intent intent = new Intent(Login.this, Employee.class);
                        startActivity(intent);
                        finish();
                    }
                    if(role.equals("Admin")) {
                        Intent intent = new Intent(Login.this, Admin.class);
                        startActivity(intent);
                        finish();
                    }

                }

                else
                {
                    Toast.makeText(Login.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}
