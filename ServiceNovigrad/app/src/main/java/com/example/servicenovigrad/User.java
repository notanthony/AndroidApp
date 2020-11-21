package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public abstract class User extends AppCompatActivity {
    protected FirebaseAuth fAuth;
    protected DatabaseReference userDataRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        final TextView welcome = findViewById(R.id.welcomeUser);
        fAuth = FirebaseAuth.getInstance();
        userDataRef = FirebaseDatabase.getInstance().getReference("UserData").child(fAuth.getCurrentUser().getUid());
        userDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserData data = dataSnapshot.getValue(UserData.class);
                welcome.setText("Welcome " + data.getName() + "!\nYou are logged in as \"" + UserData.roleToString(data.getRole()) + "\"");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    protected abstract int getLayoutResourceId();

    public void onLogoutButtonClicked(View view){
        fAuth.signOut();
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
