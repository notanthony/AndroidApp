package com.example.servicenovigrad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin extends User {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_admin;
    }

//    public void onClickAdmin(View view) {
//        switch (view.getId()) {
//            case R.id.aAddService: {
//                Intent intent = new Intent(this, Service.class);
//                startActivity(intent);
//                break;
//            }
//            case R.id.aEditService: {
//                Intent intent = new Intent(this, Service.class);
//                startActivity(intent);
//                break;
//            }
//            case R.id.aRemoveService: {
//                Intent intent = new Intent(this, Service.class);
//                startActivity(intent);
//                break;
//            }
//        }
//    }
}
