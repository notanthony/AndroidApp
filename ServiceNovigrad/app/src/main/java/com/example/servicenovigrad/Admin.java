package com.example.servicenovigrad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin extends User  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_admin;
    }

    public void onClickAdminAddService(View view) {
        Intent intent = new Intent(this, AdminAddService.class);
        startActivity(intent);
    }

    public void onClickAdminEditService(View view) {
        Intent intent = new Intent(this, AdminEditService.class);
        startActivity(intent);
    }

    public void onClickAdminRemoveService(View view) {
        Intent intent = new Intent(this, AdminRemoveService.class);
        startActivity(intent);
    }
    
}
