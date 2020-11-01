package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private FirebaseAuth fAuth;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button registerButton = findViewById(R.id.mregister);
        registerButton.setOnClickListener(this);
        Button loginButton = findViewById(R.id.mlogin);
        loginButton.setOnClickListener(this);
        fAuth = FirebaseAuth.getInstance();
        fAuth.createUserWithEmailAndPassword("admin@admin.ca", "adminpassword").addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    String id = fAuth.getCurrentUser().getUid();
                    FirebaseDatabase.getInstance().getReference("UserData").child(id).setValue(new UserData("Admin", UserData.UserRole.ADMIN, id, "admin@admin.ca"));
                    Toast.makeText(MainActivity.this,"Admin Created",Toast.LENGTH_SHORT).show();
                    fAuth.signOut();
                }
            }
        });
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
