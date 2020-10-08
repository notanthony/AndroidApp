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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    FirebaseAuth fAuth;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button registerButton = (Button)findViewById(R.id.mregister);
        registerButton.setOnClickListener(this);
        Button loginButton = (Button)findViewById(R.id.mlogin);
        loginButton.setOnClickListener(this);
        fAuth = FirebaseAuth.getInstance();
	    try {
            fAuth.createUserWithEmailAndPassword("admin@admin.com", "adminadmin").addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String roleAndName = "admin|admin";
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(roleAndName)
                                .build();
                        user.updateProfile(profileUpdates);
                        fAuth.signOut();
                    }
                }
            });
	    } finally  {
	        //any errors that pop up would shouldnt be from the code...
    	}
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
