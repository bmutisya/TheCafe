package com.example.thecafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseOne extends AppCompatActivity {
    private Button Chef,Customer,DeliveryPerson;
    Intent intent;
    String type;
    ConstraintLayout bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);
        getSupportActionBar().hide();
        AnimationDrawable animationDrawable=new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img1),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img3),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img4),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img5),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img6),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img7),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img8),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img9),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img10),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg3),3000);
        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setEnterFadeDuration(1600);
        bgimage=findViewById(R.id.back3);
        bgimage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();
        intent=getIntent();
        type=intent.getStringExtra("Home").toString().trim();
        Chef=findViewById(R.id.chef);
        DeliveryPerson=findViewById(R.id.delivery);
        Customer=findViewById(R.id.customer);

        Chef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("Email")){
                    Intent loginemail= new Intent(ChooseOne.this,ChefLogin.class);
                    startActivity(loginemail);
                    finish();
                }
                if (type.equals("Phone")){
                    Intent loginphone= new Intent(ChooseOne.this,ChefLoginPhone.class);
                    startActivity(loginphone);
                    finish();
                } if (type.equals("SignUp")){
                    Intent Register= new Intent(ChooseOne.this,ChefRegistration.class);
                    startActivity(Register);
                    finish();
                }
            }
        });
        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("Email")){
                    Intent loginemailcust= new Intent(ChooseOne.this,Login.class);
                    startActivity(loginemailcust);
                    finish();
                }
                if (type.equals("Phone")){
                    Intent loginphonecust= new Intent(ChooseOne.this,LoginPhone.class);
                    startActivity(loginphonecust);
                    finish();
                } if (type.equals("SignUp")){
                    Intent Registercust= new Intent(ChooseOne.this,Registration.class);
                    startActivity(Registercust);
                    finish();
                }

            }
        });
        DeliveryPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("Email")){
                    Intent loginemail= new Intent(ChooseOne.this,DeliveryLogin.class);
                    startActivity(loginemail);
                    finish();
                }
                if (type.equals("Phone")){
                    Intent loginphone= new Intent(ChooseOne.this,DeliveryLoginPhone.class);
                    startActivity(loginphone);
                    finish();
                } if (type.equals("SignUp")){
                    Intent Registerdelivery= new Intent(ChooseOne.this,DeliveryRegistration.class);
                    startActivity(Registerdelivery);
                    finish();
                }
            }
        });

            }
}