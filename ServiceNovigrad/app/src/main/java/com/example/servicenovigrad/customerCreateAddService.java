package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class customerCreateAddService extends AppCompatActivity {
    ArrayList<Service> servicesOffered;
    String employeeId;
    FirebaseAuth fAuth;
    DatabaseReference servicesOfferedbyBranch;
    DatabaseReference addServiceRequest;
    ArrayList<String> form;
    List<Service> listOfServicesOffered;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button chooseFile;
    Button uploadFile;
    Button submitRequest;
    EditText text1;
    EditText text2;
    EditText text3;
    EditText text4;
    EditText text5;
    EditText text6;
    Button[] buttons;
    EditText[] editTexts;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    String customerId;
//    FirebaseStorage storage;
//    StorageReference storageReference;
    String storageFilePath;
    int serviceIndex;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_customer_create_add_service);
//        button1= findViewById(R.id.button1);
//        button2  = findViewById(R.id.button2);
//        button3 = findViewById(R.id.button3);
//        button4 = findViewById(R.id.button4);
//        button5 = findViewById(R.id.button5);
//        button6 = findViewById(R.id.button6);
//        chooseFile = findViewById(R.id.choosefile);
//        uploadFile= findViewById(R.id.uploadfile);
//        submitRequest= findViewById(R.id.submitrequest);
//        text1= findViewById(R.id.textone);
//        text2= findViewById(R.id.texttwo);
//        text3= findViewById(R.id.textthree);
//        text4 = findViewById(R.id.textfour);
//        text5= findViewById(R.id.textfive);
//        text6= findViewById(R.id.textsix);
//        buttons = new Button[]{button1, button2, button3, button4, button5, button6};
//        editTexts = new EditText[]{text1, text2, text3, text4, text5, text6};
//        for(int i=0; i<6; i++){
//
//                buttons[i].setVisibility(View.INVISIBLE);
//                editTexts[i].setVisibility(View.INVISIBLE);
//        }
//        fAuth = FirebaseAuth.getInstance();
//        customerId = fAuth.getCurrentUser().getUid();
//        addServiceRequest = FirebaseDatabase.getInstance().getReference(employeeId+"/ServiceRequests/Pending");
//        servicesOfferedbyBranch = FirebaseDatabase.getInstance().getReference(employeeId+"servicesOffered");
//        storage = FirebaseStorage.getInstance();
//        storageReference = storage.getReference();
        servicesOfferedbyBranch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                listOfServicesOffered.clear();
                for (DataSnapshot postSnapshot : dataSnapShot.getChildren()) {
                    Service service = postSnapshot.getValue(Service.class);
                    listOfServicesOffered.add(service);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataBaseError) {

            }
        });
        //making the buttons visible according to listOfServicesOffered size and setting button text accordingly
    for(int j=0; j<listOfServicesOffered.size(); j++){

                buttons[j].setVisibility(View.VISIBLE);
                buttons[j].setText(listOfServicesOffered.get(j).getName());
    }

    }

    public void onButton1Clicked(View v){
        for(int i=0; i< listOfServicesOffered.get(0).getForms().size(); i++){

           editTexts[i].setVisibility(View.VISIBLE);
           editTexts[i].setHint(listOfServicesOffered.get(0).getForms().get(i));
           serviceIndex=0;

        }
    }
    public void onButton2Clicked(View v){
        for(int i=0; i< listOfServicesOffered.get(1).getForms().size(); i++){
            editTexts[i].setVisibility(View.VISIBLE);
            editTexts[i].setHint(listOfServicesOffered.get(1).getForms().get(i));
            serviceIndex=1;

        }
    }
    public void onButton3Clicked(View v){
        for(int i=0; i< listOfServicesOffered.get(2).getForms().size(); i++){
            editTexts[i].setVisibility(View.VISIBLE);
            editTexts[i].setHint(listOfServicesOffered.get(2).getForms().get(i));
            serviceIndex=2;

        }
    }
    public void onButton4Clicked(View v){
        for(int i=0; i< listOfServicesOffered.get(3).getForms().size(); i++){
            editTexts[i].setVisibility(View.VISIBLE);
            editTexts[i].setHint(listOfServicesOffered.get(3).getForms().get(i));
            serviceIndex=3;

        }
    }
    public void onButton5Clicked(View v){
        for(int i=0; i< listOfServicesOffered.get(4).getForms().size(); i++){
            editTexts[i].setVisibility(View.VISIBLE);
            editTexts[i].setHint(listOfServicesOffered.get(4).getForms().get(i));
            serviceIndex=4;

        }

    }

    public void onButton6Clicked(View v){
        for(int i=0; i< listOfServicesOffered.get(5).getForms().size(); i++){
            editTexts[i].setVisibility(View.VISIBLE);
            editTexts[i].setHint(listOfServicesOffered.get(5).getForms().get(i));
            serviceIndex=5;

        }
    }
    public void onChooseFileClicked(View v){selectFile();}

//    public void onUploadFileClicked(View V){uploadFile();}

    public void onSubmitRequestClicked(View v){submitRequest();}

    public void selectFile(){
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);


        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();

        }
    }
//    public void uploadFile(){
//        if (filePath != null) {
//
//
//            // Defining the child of storageReference
//            StorageReference ref
//                    = storageReference
//                    .child( customerId+ UUID.randomUUID().toString());
//
//            // adding listeners on upload or failure of image
//            ref.putFile(filePath)
//                    .addOnSuccessListener(
//                            new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
//                                {
//
//                                    Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
//                                    if(downloadUri.isSuccessful()){ storageFilePath = downloadUri.getResult().toString();}
//                                    Toast.makeText(customerCreateAddService.this,
//                                                    "Image Uploaded!!",
//                                                    Toast.LENGTH_SHORT)
//                                            .show();
//
//
//                                }
//                            })
//
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e)
//                        {
//                            Toast
//                                    .makeText(customerCreateAddService.this,
//                                            "Failed " + e.getMessage(),
//                                            Toast.LENGTH_SHORT)
//                                    .show();
//                        }
//                    });
//        }
//    }
    public void submitRequest(){
        ArrayList<String> form = new ArrayList<>();
        for(int i=0; i< listOfServicesOffered.get(serviceIndex).getForms().size(); i++){
            form.add(editTexts[i].getText().toString());
        }
       ServiceRequest newRequest = new ServiceRequest(form, storageFilePath);
        addServiceRequest.child(customerId).setValue(newRequest);

    }

    

}