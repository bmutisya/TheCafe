package com.example.thecafe;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView2, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);
        imageView=findViewById(R.id.imageView);
        imageView.animate().alpha(0f).setDuration(0);
        textView1.animate().alpha(0f).setDuration(0);
        textView2.animate().alpha(0f).setDuration(0);
        imageView.animate().alpha(1f).setDuration(100).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                textView1.animate().alpha(1f).setDuration(80);
                textView2.animate().alpha(1f).setDuration(80);

            }
        });
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent intent=new Intent(MainActivity.this,MainMenu.class);
                 startActivity(intent);
                 finish();

             }
         },4000);


    }
}