package com.example.thecafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

public class ChefRegistration extends AppCompatActivity {
    String [] Nairobi={"Githurai","Kahawa","Ruiru"};
    String [] Mombasa={"Pipeline","Donhom","kayole"};
    TextInputLayout Fname,Lname,Email,Pass,Cpass,Mobileno,Houseno,area,Pincode;
    Spinner Statespin,CitySpin;
    Button signup,email,phone;
    CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname,lname,emailid,password,confpassword,mobile,house,Area,pincode,role="chef",statee,cityy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_registration);
        Fname=findViewById(R.id.Firstname);
        Lname=findViewById(R.id.Lastname);
        Email=findViewById(R.id.Email);
        Pass=findViewById(R.id.pwd);
        Cpass=findViewById(R.id.Cpwd);
        Mobileno=findViewById(R.id.Mobileno);
        Houseno=findViewById(R.id.Houseno);
        area=findViewById(R.id.Area);
        Pincode=findViewById(R.id.Pincode);
        Statespin=findViewById(R.id.state);
        CitySpin=findViewById(R.id.Citys);
        signup=findViewById(R.id.Signup);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        Cpp=findViewById(R.id.countrycode);

        Statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value=parent.getItemAtPosition(position);
               statee=value.toString().trim();
                if(statee.equals("Nairobi")){
                    ArrayList<String> list=new ArrayList<>();
                    for (String cities : Nairobi){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(ChefRegistration.this, android.R.layout.simple_spinner_item,list);
                    CitySpin.setAdapter(arrayAdapter);
                }
                if(statee.equals("Mombasa")){
                    ArrayList<String> list=new ArrayList<>();
                    for (String cities : Mombasa){
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChefRegistration.this, android.R.layout.simple_spinner_item,list);
                    CitySpin.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        CitySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value =parent.getItemAtPosition(position);
                cityy=value.toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        databaseReference=firebaseDatabase.getInstance().getReference("Chef");
        FAuth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=Fname.getEditText().getText().toString().trim();
                lname=Lname.getEditText().getText().toString().trim();
                emailid=Email.getEditText().getText().toString().trim();
                mobile=Mobileno.getEditText().getText().toString().trim();
                password=Pass.getEditText().getText().toString().trim();
                confpassword=Cpass.getEditText().getText().toString().trim();
                Area=area.getEditText().getText().toString().trim();
                house=Houseno.getEditText().getText().toString().trim();
                pincode=Pincode.getEditText().getText().toString().trim();

                if(isValid()){
                    final ProgressDialog mDialog= new ProgressDialog(ChefRegistration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait.....");
                    mDialog.show();
                    FAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String useridd=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference=FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String, String> hashMap =new HashMap<>();
                                hashMap.put("Role",role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull  Task<Void> task) {
                                        HashMap<String,String> hashMap1= new HashMap<>();
                                        hashMap1.put("Mobile No", mobile);
                                        hashMap1.put("First Name", fname);
                                        hashMap1.put("Last Name", lname);
                                        hashMap1.put("EmailId", emailid);
                                        hashMap1.put("City", cityy);
                                        hashMap1.put("Area", Area);
                                        hashMap1.put("Password", password);
                                        hashMap1.put("Pincode", pincode);
                                        hashMap1.put("State", statee);
                                        hashMap1.put("Confirm Password", confpassword);
                                        hashMap1.put("House", house);
                                        firebaseDatabase.getInstance().getReference("Chef").
                                                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                                setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull  Task<Void> task) {
                                                mDialog.dismiss();

                                                FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull  Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            AlertDialog.Builder builder=new AlertDialog.Builder(ChefRegistration.this);
                                                            builder.setMessage("You have Registered! Please verify Your Email");
                                                            builder.setCancelable(false);
                                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.dismiss();

                                                                }
                                                            });
                                                            AlertDialog Alert=builder.create();
                                                            Alert.show();
                                                        }else{
                                                            mDialog.dismiss();
                                                            ReusableCodeForAll.ShowAlert(ChefRegistration.this,"Error",task.getException().getMessage());
                                                        }
                                                    }
                                                });
                                            }
                                        });


                                    }
                                });
                            }

                        }
                    });
                }
            }
        });

    }
    //will check the email formart entered
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //isvalid will check if email is correctly done and there are no empty fields and correct phone number;
    public boolean isValid(){
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        Mobileno.setErrorEnabled(false);
        Mobileno.setError("");
        Cpass.setErrorEnabled(false);
        Cpass.setError("");
        area.setErrorEnabled(false);
        area.setError("");
        Houseno.setErrorEnabled(false);
        Houseno.setError("");
        Pincode.setErrorEnabled(false);
        Pincode.setError("");

        boolean isValid=false,isValidname=false,isValidhouseno=false,isValidlname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false,isValidarea=false,isValidpincode=false;
        if(TextUtils.isEmpty(fname)){
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");
        }else{
            isValidname=true;
        }
        if(TextUtils.isEmpty(lname)){
            Lname.setErrorEnabled(true);
            Lname.setError("Enter Last Name");
        }else{
            isValidlname=true;
        }
        if(TextUtils.isEmpty(emailid)){
            Email.setErrorEnabled(true);
            Email.setError("Email Address is required");
        }else {
            if (emailid.matches(emailpattern)) {
                isValidemail = true;
            } else {
                Email.setErrorEnabled(true);
                Email.setError("Enter a Valid Email Address");
            }
        }
        if(TextUtils.isEmpty(password)){
                Pass.setErrorEnabled(true);
                Pass.setError("Enter Password");
        }else {
            if (password.length()<8){
                Pass.setErrorEnabled(true);
                Pass.setError("Weak Password");
            }else {
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(confpassword)){
            Cpass.setErrorEnabled(true);
            Cpass.setError("Enter Password Again");
        }else{
            if (!password.equals(confpassword)){
                Cpass.setErrorEnabled(true);
                Cpass.setError("Password Dosent Match");
            }else{
                isValidconfpassword=true;
            }
        }
        if(TextUtils.isEmpty(mobile)){
            Mobileno.setErrorEnabled(true);
            Mobileno.setError("Enter Phone Number");
        }else{
            if (mobile.length()<10){
                Mobileno.setErrorEnabled(true);
                Mobileno.setError("Invalid Mobile Number");
            } else {
                isValidmobilenum = true;
            }
        }
        if(TextUtils.isEmpty(Area)){
            area.setErrorEnabled(true);
            area.setError("Area is Required");
        }else{
            isValidarea=true;
        }
        if(TextUtils.isEmpty(pincode)){
            Pincode.setErrorEnabled(true);
            Pincode.setError("Please Enter Pincode");
        }else{
            isValidpincode=true;
        }
        if(TextUtils.isEmpty(house)){
            Houseno.setErrorEnabled(true);
            Houseno.setError("House field cant be empty");
        }else{
            isValidhouseno=true;
        }
        isValid=(isValidarea && isValidpincode && isValidconfpassword && isValidpassword && isValidemail && isValidname &&isValidmobilenum && isValidhouseno && isValidlname)? true:false;
        return isValid;

        }










    }
