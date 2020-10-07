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
	try {
		await FirebaseAuth.instance.getUserByEmail("admin@admin.com");
	} on FirebaseAuthException catch  (e) {
		createAdmin();
	}
    }
	
	private static void createAdmin() {
		Firebase fAuth = FirebaseAuth.getInstance();
		fAuth.createUserWithEmailAndPassword("admin@admin.com", "adminadmin").addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String roleAndName = "admin | admin";

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()

                            .setDisplayName(roleAndName)
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this,"User Profile Updated",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                else
                {
                    Toast.makeText(Register.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

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
