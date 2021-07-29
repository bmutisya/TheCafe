package com.example.thecafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView2, textView1;
    FirebaseAuth Fauth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

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
                 Fauth=FirebaseAuth.getInstance();
                 if (Fauth.getCurrentUser()!=null){
                     if (Fauth.getCurrentUser().isEmailVerified()){
                         Fauth=FirebaseAuth.getInstance();
                         databaseReference=FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getUid()+"/Role");
                         databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                             @Override
                             public void onDataChange(@NonNull DataSnapshot snapshot) {
                                 String role=snapshot.getValue(String.class);
                                 if (role.equals("chef")){
                                     startActivity(new Intent(MainActivity.this,ChefFoodPanel_BottomNavigation.class));
                                     finish();
                                 }
                             }

                             @Override
                             public void onCancelled(@NonNull  DatabaseError error) {
                                 Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                             }
                         });

                     } else {
                         AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                         builder.setMessage("Check if you have verified your email, please verify");
                         builder.setCancelable(false);
                         builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.dismiss();
                                 Intent intent=new Intent(MainActivity.this,MainMenu.class);
                                 startActivity(intent);
                                 finish();

                             }
                         });
                         AlertDialog alertDialog= builder.create();
                         alertDialog.show();
                         Fauth.signOut();

                     }

                 }else {
                 Intent intent=new Intent(MainActivity.this,MainMenu.class);
                 startActivity(intent);
                 finish();

             }
             }
         },3000);


    }
}