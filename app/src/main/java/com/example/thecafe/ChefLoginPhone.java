package com.example.thecafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class ChefLoginPhone extends AppCompatActivity {
    EditText num;
    Button sendotp, signinemail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_login_phone);
        num=findViewById(R.id.chefverifynumber);
        sendotp=findViewById(R.id.chefotp);
        signinemail=findViewById(R.id.chefemailbtn);
        signup=findViewById(R.id.chefphonenoaccount);
        cpp=findViewById(R.id.countcodepicker);
        Fauth=FirebaseAuth.getInstance();
        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=num.getText().toString().trim();
                String Phonenum=cpp.getSelectedCountryCodeWithPlus()+number;
                Intent b= new Intent(ChefLoginPhone.this,ChefSendOtp.class);
                b.putExtra("Phonenum",Phonenum);
                startActivity(b);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChefLoginPhone.this,ChefRegistration.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChefLoginPhone.this,ChefLogin.class));
                finish();

            }
        });
    }
}