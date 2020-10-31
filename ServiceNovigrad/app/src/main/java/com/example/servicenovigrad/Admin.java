package com.example.servicenovigrad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends User implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button addServiceButton = findViewById(R.id.addServiceButton);
        addServiceButton.setOnClickListener(this);
        Button editServiceButton = findViewById(R.id.editServiceButton);
        editServiceButton.setOnClickListener(this);
        Button removeServiceButton = findViewById(R.id.disableUserButton);
        removeServiceButton.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_admin;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editServiceButton: {
                Intent intent = new Intent(this, AdminEditService.class);
                startActivity(intent);
                break;
            }
            case R.id.addServiceButton: {
                Intent intent = new Intent(this, AdminAddService.class);
                startActivity(intent);
                break;
            }
            case R.id.disableUserButton: {
                Intent intent = new Intent(this, AdminDisableUser.class);
                startActivity(intent);
                break;
            }
        }
    }
}
