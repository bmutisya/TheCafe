package com.example.thecafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity {
    private Button signinemail, signinphone,signup;
    ImageView bgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final Animation zoomin= AnimationUtils.loadAnimation(this,R.anim.zoomin);
        final Animation zoomout=AnimationUtils.loadAnimation(this,R.anim.zoomout);
        bgImage=findViewById(R.id.back2);
        bgImage.setAnimation(zoomin);
        bgImage.setAnimation(zoomout);

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgImage.startAnimation(zoomin);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        zoomin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgImage.startAnimation(zoomout);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        signinemail=findViewById(R.id.signWithEmail);
        signinphone=findViewById(R.id.signWithPhone);
        signup=findViewById(R.id.signup);
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinemail=new Intent(MainMenu.this, ChooseOne.class);
                signinemail.putExtra("Home","Email");
                startActivity(signinemail);
                finish();
            }
        });
        signinphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinphone= new Intent(MainMenu.this,ChooseOne.class);
                signinphone.putExtra("Home","Email");
                startActivity(signinphone);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup=new Intent(MainMenu.this,ChooseOne.class);
                signup.putExtra("Home","Email");
                startActivity(signup);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}