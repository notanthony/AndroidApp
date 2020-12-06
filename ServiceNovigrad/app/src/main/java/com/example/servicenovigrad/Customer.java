package com.example.servicenovigrad;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer extends User implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button searchBranchesButton = findViewById(R.id.searchBranchesButton);
        searchBranchesButton.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_customer;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchBranchesButton: {
                Intent intent = new Intent(this, CustomerSearchBranches.class);
                startActivity(intent);
                break;
            }
            case R.id.viewReqeustButton: {
                Intent intent = new Intent(this, CustomerViewRequests.class);
                startActivity(intent);
                break;
            }
        }
    }
}
