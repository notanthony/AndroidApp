package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText email, password;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
        progressBar = findViewById(R.id.lprogressbar);
        fAuth = FirebaseAuth.getInstance();
    }

    public void onLoginButtonClicked(View view) {
        String inputEmail = email.getText().toString().trim();
        String inputPassword = password.getText().toString().trim();
        if(inputEmail.equals("admin")  && inputPassword.equals("admin")) {
            inputEmail = "admin@admin.org";
            inputPassword = "adminpassword";
        }
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
                    DatabaseReference userDataRef = FirebaseDatabase.getInstance().getReference("UserData").child(fAuth.getCurrentUser().getUid());
                    userDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            UserData user = dataSnapshot.getValue(UserData.class);
                            if (user.isActive()) {
                                switch(user.getRole()) {

                                    case CUSTOMER: {
                                        Intent intent = new Intent(Login.this, Customer.class);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    }
                                    case EMPLOYEE: {
                                        Intent intent = new Intent(Login.this, Employee.class);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    }
                                    case ADMIN: {
                                        Intent intent = new Intent(Login.this, Admin.class);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    }
                                    default: {
                                        Toast.makeText(Login.this,"Could not find user info",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(Login.this,"This user is disabled",Toast.LENGTH_SHORT).show();
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                else
                {
                    Toast.makeText(Login.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
