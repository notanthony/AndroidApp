package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminDisableUser extends AppCompatActivity {
    DatabaseReference databaseServices;
    List<UserData> users;
    ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_disable_user);
        databaseServices = FirebaseDatabase.getInstance().getReference("UserData");
        listViewUsers = (ListView) findViewById(R.id.listView);
        ((TextView) findViewById(R.id.dataType)).setText("User");
        ((TextView) findViewById(R.id.instructions)).setText("Tap and hold on the users you want to disable/enable");
        users = new ArrayList<UserData>();

        listViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminDisableUser.this);
                final UserData user = users.get(i);
                builder.setCancelable(true);
                builder.setTitle((user.isActive()? "Disable":"Activate")+" this user");
                builder.setMessage(user.toString());
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                disableUser(user);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AdminDisableUser.this,"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }


    @Override
    protected void onStart() {

        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                users.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    UserData user = postSnapshot.getValue(UserData.class);
                    System.out.println("email is" + user.email);
                    users.add(user);
                }
                ArrayAdapter<UserData> usersAdapter =
                        new ArrayAdapter<UserData>(AdminDisableUser.this, android.R.layout.simple_list_item_1 , users);
                listViewUsers.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }


    private void disableUser(UserData user) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("UserData").child(user.getId());
        if (user.getRole() == UserData.UserRole.ADMIN) {
            Toast.makeText(getApplicationContext(), "User Cannot Be Disabled", Toast.LENGTH_LONG).show();
            return;
        }
        user.invertActive();
        dR.setValue(user);
        Toast.makeText(getApplicationContext(), "User " + (!user.isActive()? "Disabled":"Activated"), Toast.LENGTH_LONG).show();
    }
}
