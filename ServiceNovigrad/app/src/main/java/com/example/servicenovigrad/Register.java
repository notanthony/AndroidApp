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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText name, email, password, password2;
    private FirebaseAuth fAuth;
    private RadioGroup radioGroup;
    private RadioButton customerButton;
    private RadioButton employeeButton;
    private UserData.UserRole userRole = UserData.UserRole.CUSTOMER;
    private ProgressBar progressBar;
    private DatabaseReference fDataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.rname);
        email = findViewById(R.id.remail);
        password = findViewById(R.id.rpassword);
        password2 = findViewById(R.id.rpassword2);
        radioGroup= findViewById(R.id.rradiogroup);
        radioGroup.check(R.id.rcustomerbutton);
        customerButton = findViewById(R.id.rcustomerbutton);
        employeeButton = findViewById(R.id.remployeebutton);
        progressBar=findViewById(R.id.rprogressbar);
        fAuth = FirebaseAuth.getInstance();
        fDataRef = FirebaseDatabase.getInstance().getReference();
    }

    public void onCustomerButtonClicked(View view) {
        userRole= UserData.UserRole.CUSTOMER;
    }

    public void onEmployeeButtonClicked(View view) {
        userRole= UserData.UserRole.EMPLOYEE;
    }

    public void onRegisterButtonClicked(View view){
        final String inputName = name.getText().toString().trim();
        String inputEmail=email.getText().toString().trim();
        String inputPassword = password.getText().toString().trim();
        String inputPassword2= password2.getText().toString().trim();
        if(TextUtils.isEmpty(inputName)){name.setError("Name is Required. ");return;}
        if(TextUtils.isEmpty(inputEmail)){email.setError("Email is Required. ");return;}
        if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){email.setError("Valid Email Required. ");return;}
        if(TextUtils.isEmpty(inputPassword)){password.setError("Password is Required. ");return;}
        if (inputPassword.length() < 6){password.setError("Password Length of 6 Characters Required. "); return;}
        if(TextUtils.isEmpty(inputPassword2)){password2.setError("ReEnter Password. ");return;}
        if(! inputPassword.equals(inputPassword2)){password2.setError("Passwords Don't Match. "); return;}
        progressBar.setVisibility(View.VISIBLE);
        fAuth.createUserWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    fDataRef.child("users").child(fAuth.getUid()).setValue(new UserData(inputName, userRole));
                    Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                    finish();
                    }
                else
                {
                    Toast.makeText(Register.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
}
