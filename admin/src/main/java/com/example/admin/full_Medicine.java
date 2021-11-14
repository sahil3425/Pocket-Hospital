package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class full_Medicine extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full__medicine);
        imageView=findViewById(R.id.medicine_image);
        String Url=getIntent().getStringExtra("image");
        Glide.with(getApplicationContext())
                .load(Url)
                .into(imageView);
    }
}