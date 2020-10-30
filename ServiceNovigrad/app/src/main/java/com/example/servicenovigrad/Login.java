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

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText email, password;
    private FirebaseAuth fAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    List<UserData> UserDataList;
    DatabaseReference dataBaseUserData;


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

        UserDataList = new ArrayList<>();
        dataBaseUserData = FirebaseDatabase.getInstance().getReference("UserData");

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
        //necessary for the current way we implemented variable storing to work
        //since variables are tied to the roleAndName
        if (inputEmail.equals("admin")&& inputPassword.equals("admin")) {
            inputEmail = "admin@admin.com";
            inputPassword = "adminadmin";
        }
        fAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {


                if (task.isSuccessful())
                {
                    Toast.makeText(Login.this,"User Logged In",Toast.LENGTH_LONG).show();
                    String displayName = fAuth.getCurrentUser().getDisplayName();
                    String email = fAuth.getCurrentUser().getEmail();
                    UserData  currentLoggedUser = searchUserEmail(email);
                    if (currentLoggedUser != null && currentLoggedUser.getAccountStatus().equals("disabled")){
                        Toast.makeText(Login.this,"User Account is Disabled "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, AccountDisabled.class);
                        startActivity(intent);
                        finish();
                    }
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
    public UserData searchUserEmail(String email){
        for(int i = 0; i<UserDataList.size(); i++){if(UserDataList.get(i).getEmail().equals(email)){return UserDataList.get(i);}}
        return null;
    }
}
