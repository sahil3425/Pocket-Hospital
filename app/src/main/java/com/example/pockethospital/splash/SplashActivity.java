package com.example.pockethospital.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.pockethospital.Login.LoginActivity;
import com.example.pockethospital.MainActivity;
import com.example.pockethospital.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    final static String KEY_NAME="mypref";
    final static String KEY_CHECKBOX="rememberme";
    final static String email_verified="false";
    final static String google_verified="false";
    final static String KEY_UMail="android@gmail.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences(KEY_NAME, MODE_PRIVATE);
        String verified=sharedPreferences.getString(email_verified, "");
        String google_verified1=sharedPreferences.getString(google_verified, "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               if(mAuth.getCurrentUser()!=null && verified.equals("true") ) {
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                else if(google_verified1.equals("true")) {

                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                else{

                    Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }
            }
        },3000);
    }



}