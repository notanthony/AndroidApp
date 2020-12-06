package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.TimeZone;

public class CustomerSearchBranches extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference branchDataRef;
    ArrayList<EmployeeData> branches;
    ListView listViewBranches;
    ArrayAdapter<EmployeeData> branchAdapter;
    private float avgBranchRating;
    private float totalBranchRatingSum;
    ArrayList<Float> branchRatings = new ArrayList<>();
    ArrayList<String> comments = new ArrayList<>();


    EditText openIncreasingSort;
    EditText openDecreasingSort;
    EditText closeIncreasingSort;
    EditText closeDecreasingSort;
    EditText citySort;
    EditText serviceSort;
    EditText postalCodeSort;

    Button openIncreasingSortButton;
    Button openDecreasingSortButton;
    Button closeIncreasingSortButton;
    Button closeDecreasingSortButton;
    Button citySortButton;
    Button serviceSortButton;
    Button postalCodeSortButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search_branches);

        openIncreasingSort = (EditText) findViewById(R.id.openIncreasing);
        openDecreasingSort = (EditText) findViewById(R.id.openDecreasing);
        closeIncreasingSort = (EditText) findViewById(R.id.closeIncreasing);
        closeDecreasingSort = (EditText) findViewById(R.id.closeDecreasing);
        citySort = (EditText) findViewById(R.id.city);
        serviceSort = (EditText) findViewById(R.id.service);
        postalCodeSort = (EditText) findViewById(R.id.postalCode);

        openIncreasingSortButton = (Button) findViewById(R.id.openIncButton);
        openDecreasingSortButton = (Button) findViewById(R.id.openDecButton);
        closeIncreasingSortButton = (Button) findViewById(R.id.closeIncButton);
        closeDecreasingSortButton = (Button) findViewById(R.id.closeDecButton);
        citySortButton = (Button) findViewById(R.id.searchCityButton);
        serviceSortButton = (Button) findViewById(R.id.searchServiceButton);
        postalCodeSortButton = (Button) findViewById(R.id.searchPostalCodeButton);

        openIncreasingSort.setOnClickListener(this);
        openDecreasingSortButton.setOnClickListener(this);
        closeIncreasingSortButton.setOnClickListener(this);
        closeDecreasingSortButton.setOnClickListener(this);
        citySortButton.setOnClickListener(this);
        serviceSortButton.setOnClickListener(this);
        postalCodeSortButton.setOnClickListener(this);

        branchDataRef = FirebaseDatabase.getInstance().getReference("UserData");
        listViewBranches = (ListView) findViewById(R.id.listViewBranches);
        branches = new ArrayList<>();

        listViewBranches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (CustomerSearchBranches.this, CustomerSearchBranches.class);
                intent.putExtra( "branch" , branches.get(position).getId());
                startActivity(intent);
                finish();
            }
        });

        listViewBranches.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                EmployeeData branch = branches.get(i);
//                showUpdateDeleteDialog(service.getId(),service);
                rateBranchDialog(branch.getId(),branch); //rate branch
                return true;
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        branchDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                branches.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    EmployeeData branch = postSnapshot.getValue(EmployeeData.class);
                    if (branch.getRole() == UserData.UserRole.EMPLOYEE && branch.isActive()) {
                        branches.add(branch);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                listViewBranches.setAdapter(branchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
    }

    private void rateBranchDialog(final String branchId, final EmployeeData b){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.rate_branch_dialog, null);
        dialogBuilder.setView(dialogView);

        final RatingBar ratingRatingBar = (RatingBar) findViewById(R.id.rating_rating_bar);
        Button submitButton = (Button) findViewById(R.id.submit_button);
        final TextView ratingDisplayTextView = (TextView) findViewById(R.id.rating_display_text_View);

        //final EditText editFeedback = (EditText) findViewById(R.id.feedback);
        final EditText editFeedback = (EditText) dialogView.findViewById(R.id.feedback);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment;
                float rating;
                rating=ratingRatingBar.getRating();
                comment=editFeedback.getText().toString().trim();
                ratingDisplayTextView.setText("You rated this branch: " + rating+"/5"+"\n\nThanks for your feedback!");
                //ratingDisplayTextView.setText("You rated this branch: " + rating+"/5"+"\n\nThanks for your feedback!\n\n"+custComment);

                //keep all attributes of the branch the same except the rating and customer comment
                String name = b.getBranchName();
                UserData.UserRole role = b.getUserRole();
                String email = b.getEmail();
                String phoneNumber = b.getPhoneNumber();
                Address address = b.getAddress();
                ArrayList<String> opening = b.getOpening();
                ArrayList<String> closing = b.getClosing();

                float avgBranchRating = b.getAvgBranchRating();

                ArrayList<Float> branchRatings = b.getBranchRatings();
                branchRatings.add(rating);

                ArrayList<String> comments = b.getComments();
                comments.add(comment);

                totalBranchRatingSum=0; //total number of ratings for the branch
                for(int i = 0; i < branchRatings.size(); i++){
                    totalBranchRatingSum += branchRatings.get(i);}

                avgBranchRating=(totalBranchRatingSum/branchRatings.size()); //new average branch rating

                updateBranchRating(name,role,branchId,email,phoneNumber,address,opening,closing,avgBranchRating,branchRatings,comments);
            }
        });
    }

    private void updateBranchRating(String name, UserData.UserRole role, String id, String email, String phoneNumber, Address address, ArrayList<String> opening, ArrayList<String> closing, float avgBranchRating, ArrayList<Float> branchRatings, ArrayList<String> comments)  {
        //getting the specified service reference

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("branches").child(id);
        //updating service
        EmployeeData branch = new EmployeeData(name,role,id,email,phoneNumber,address,opening,closing,avgBranchRating,branchRatings,comments);
        dR.setValue(branch);

        Toast.makeText(getApplicationContext(), "Branch Updated with new rating", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openIncButton: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.openingHours(dayOfWeek(openIncreasingSort.getText().toString())));
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.openDecButton: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.openingHours(dayOfWeek(openDecreasingSort.getText().toString())).reversed());
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.closeIncButton: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.closingHours(dayOfWeek(closeDecreasingSort.getText().toString())).reversed());
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.closeDecButton: {
                //PLEASE IMPLEMENT A EDIT TEXT FIELD ASKING FOR THE DAY OF THE WEEK THEY WANNA SORT BY
                branches.sort(EmployeeData.closingHours(dayOfWeek(closeIncreasingSort.getText().toString())));
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , branches);
                break;
            }
            case R.id.searchCityButton: {
                ArrayList<EmployeeData> temp = new ArrayList<>();
                for(EmployeeData data : branches) {
                    if (data.getAddress().getCity().equals(citySort.getText().toString())) {
                        temp.add(data);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , temp);
                break;
            }
            case R.id.searchServiceButton: {
                ArrayList<EmployeeData> temp = new ArrayList<>();
                for(EmployeeData data : branches) {
                    if (data.getServiceNames().contains(serviceSort.getText().toString())) {
                        temp.add(data);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , temp);
                break;
            }
            case R.id.searchPostalCodeButton: {
                ArrayList<EmployeeData> temp = new ArrayList<>();
                for(EmployeeData data : branches) {
                    if (data.getAddress().getPostalCode().substring(0,3).equals(postalCodeSort.getText().toString())) {
                        temp.add(data);
                    }
                }
                branchAdapter =
                        new ArrayAdapter<>(CustomerSearchBranches.this, android.R.layout.simple_list_item_1 , temp);
                break;
            }
        }
        //probably redunant to have the set adapter again but im not risking it
        listViewBranches.setAdapter(branchAdapter);
        listViewBranches.invalidateViews();
        listViewBranches.refreshDrawableState();
    }

    public int dayOfWeek (String day) {
        String weekday = day.toLowerCase();
        switch (weekday) {
            case "monday":
                return 0;
            case "tuesday":
                return 1;
            case "wednesday":
                return 2;
            case "thursday":
                return 3;
            case "friday":
                return 4;
            case "saturday":
                return 5;
            case "sunday":
                return 6;
        }
        return -1;
    }

}
