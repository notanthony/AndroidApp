package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Login extends AppCompatActivity {
    TextView title, title2, register;
    Button loginButton;
    ProgressBar progressBar;
    EditText email, password;
    FirebaseAuth fAuth;
    private RadioGroup rg;
    private RadioButton customerButton;
    private RadioButton employeeButton;
    private RadioButton adminButton;
    private String accountType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
        loginButton = findViewById(R.id.llogin);
        progressBar = findViewById(R.id.lprogressbar);
        fAuth = FirebaseAuth.getInstance();
        rg = (RadioGroup) findViewById(R.id.lradiogroup);
        adminButton = findViewById(R.id.lAdminLogin);
        customerButton = findViewById(R.id.lCustomerLogin);
        employeeButton = findViewById(R.id.lEmployeeLogin);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.lCustomerLogin:
                        accountType = "customer";
                        break;
                    case R.id.lEmployeeLogin:
                        accountType = "employee";
                        break;
                    case R.id.lAdminLogin:
                        accountType = "admin";
                        break;
                }
            }
        });
    }


    public void onLoginButtonClicked(View view) {
        String inputEmail = email.getText().toString().trim();
        String inputPassword = password.getText().toString().trim();
        if (TextUtils.isEmpty(inputEmail)) {
            email.setError("Email is Required. ");
            return;
        }
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
                    Toast.makeText(Login.this,"User Logged In",Toast.LENGTH_SHORT).show();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Intent intent = new Intent(Login.this, Customer.class);
                    switch (accountType) {
                        case "customer":
                            intent = new Intent(Login.this, Customer.class);
                            break;
                        case "employee":
                            intent = new Intent(Login.this, Employee.class);
                            break;
                        case "admin":
                            intent = new Intent(Login.this, Admin.class);
                            break;
                    }

                    startActivity(intent);
                    finish();

                }

                else
                {
                    Toast.makeText(Login.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}