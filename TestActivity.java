package com.example.servicenovigrad;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Random;

public class TestActivity extends AppCompatActivity {
    final String[] randomCredentials = randomCredentialGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public static String[] randomCredentialGenerator() {
        String[] randomCredentials = new String[10];
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < targetStringLength; j++) {
                int randomLimitedInt = leftLimit + (int)
                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
                String generatedString = buffer.toString();
                if (i % 2 == 0) {
                    generatedString += "@gmail.com";
                    randomCredentials[i] = generatedString;

                }
                if (i % 2 != 0) {
                    randomCredentials[i] = generatedString;

                }

            }

        }
        return randomCredentials;
    }

    public void signWithRandomCredentials(){
        FirebaseAuth fAuth = FirebaseAuth.getInstance();


        for(int i =0; i<randomCredentials.length; i+=2){
            fAuth.createUserWithEmailAndPassword(randomCredentials[i], randomCredentials[i+1]).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {


                    if (task.isSuccessful())
                    {
                        Toast.makeText(TestActivity.this,"Success! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }


                    else
                    {
                        Toast.makeText(TestActivity.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });



        }



    }
    public void loginWithRandomCredentials(){
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        for(int i =0; i<randomCredentials.length; i+=2){

            fAuth.signInWithEmailAndPassword(randomCredentials[i], randomCredentials[i+1]).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {


                    if (task.isSuccessful())
                    {
                        Toast.makeText(TestActivity.this,"User Logged In",Toast.LENGTH_LONG).show();

                    }

                    else
                    {
                        Toast.makeText(TestActivity.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }

                }
            });




        }





    }




}