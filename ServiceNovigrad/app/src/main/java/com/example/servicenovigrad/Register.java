package com.example.servicenovigrad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText name, email, password, password2, city, postalCode, phoneNumber, street;
    private LinearLayout employeeInput;
    private Spinner dropdown;
    private FirebaseAuth fAuth;
    private RadioGroup radioGroup;
    private UserData.UserRole userRole = UserData.UserRole.CUSTOMER;
    private ProgressBar progressBar;
    private DatabaseReference fDataRef;
    private Address.Province province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.rname);
        email = findViewById(R.id.remail);
        password = findViewById(R.id.rpassword);
        password2 = findViewById(R.id.rpassword2);
        radioGroup= findViewById(R.id.rradiogroup);
        radioGroup.check(R.id.rcustomerbutton);
        city =findViewById(R.id.rcity);
        postalCode=findViewById(R.id.rpostalCode);
        phoneNumber=findViewById(R.id.rphoneNumber);
        street=findViewById(R.id.rstreet);
        progressBar=findViewById(R.id.rprogressbar);
        employeeInput = findViewById(R.id.employeeInput);
        //get the spinner from the xml.
        dropdown = findViewById(R.id.rprovince);

        //create a list of items for the spinner.
        dropdown.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Address.Province.values()));
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 province = Address.Province.valueOf(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fAuth = FirebaseAuth.getInstance();
        fDataRef = FirebaseDatabase.getInstance().getReference("UserData");
    }

    public void onCustomerButtonClicked(View view) {
        userRole= UserData.UserRole.CUSTOMER;
        employeeInput.setVisibility(View.INVISIBLE);
    }

    public void onEmployeeButtonClicked(View view) {
        userRole= UserData.UserRole.EMPLOYEE;
        employeeInput.setVisibility(View.VISIBLE);
    }

    public void onRegisterButtonClicked(View view){
        final String inputName = name.getText().toString().trim();
        final String inputEmail=email.getText().toString().trim();
        String inputPassword = password.getText().toString().trim();
        String inputPassword2= password2.getText().toString().trim();

        if(TextUtils.isEmpty(inputName)){name.setError("Name is Required. ");return;}
        if(TextUtils.isEmpty(inputEmail)){email.setError("Email is Required. ");return;}
        if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){email.setError("Valid Email Required. ");return;}
        if(TextUtils.isEmpty(inputPassword)){password.setError("Password is Required. ");return;}
        if (inputPassword.length() < 6){password.setError("Password Length of 6 Characters Required. "); return;}
        if(TextUtils.isEmpty(inputPassword2)){password2.setError("ReEnter Password. ");return;}
        if(! inputPassword.equals(inputPassword2)){password2.setError("Passwords Don't Match. "); return;}

        //employee specific variables
        String inputPhoneNumber = phoneNumber.getText().toString().trim();
        final String inputCity = city.getText().toString().trim();
        final String inputStreet = street.getText().toString().trim();
        String inputPostalCode = postalCode.getText().toString().trim();
        final Address address;
        //employee specific validation
        if (userRole == UserData.UserRole.EMPLOYEE) {
            if(TextUtils.isEmpty(inputPhoneNumber)){phoneNumber.setError("Phone number is required. ");return;}
            if (inputPhoneNumber.length() == 10) {
                inputPhoneNumber = inputPhoneNumber.substring(0, 3)+ "-"
                            + inputPhoneNumber.substring(3, 6)+ "-"
                            + inputPhoneNumber.substring(6);
            }
            if (!Pattern.matches("^(\\d{3}-){2}\\d{4}$", inputPhoneNumber)) {
                phoneNumber.setError("Phone number is invalid. ex. 1234567891");
                return;
            }

            if(TextUtils.isEmpty(inputCity)){city.setError("City is Required. ");return;}
            if(TextUtils.isEmpty(inputStreet)){street.setError("Street is Required. ");return;}

            if(TextUtils.isEmpty(inputPostalCode)){postalCode.setError("Postal Code is Required. ");return;}

            if (inputPostalCode.length()==6){
                inputPostalCode = inputPostalCode.substring(0, 3)
                        + " "
                        + inputPostalCode.substring(3);
            }
            inputPostalCode=inputPostalCode.toUpperCase();

            Pattern phonePattern = Pattern.compile("[A-Z]\\d[A-Z] \\d[A-Z]\\d");
            Matcher m = phonePattern.matcher(inputPostalCode);
            if (!m.matches()) {
                postalCode.setError("Postal code is invalid. ex.m1a1d9");
                return;
            }
        }

        final String employeePhoneNumber=inputPhoneNumber;
        final String employeePostalCode=inputPostalCode;

        progressBar.setVisibility(View.VISIBLE);
        fAuth.createUserWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    String id = fAuth.getCurrentUser().getUid();
                    if (userRole == UserData.UserRole.EMPLOYEE) {
                        fDataRef.child(id).setValue(new EmployeeData(inputName, userRole, id, inputEmail, employeePhoneNumber, new Address (province, employeePostalCode, inputStreet, inputCity), null, null));
                    } else {
                        fDataRef.child(id).setValue(new UserData(inputName, userRole, id, inputEmail));
                    }
                    Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                    finish();
                    }
                else
                {
                    Toast.makeText(Register.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
}
