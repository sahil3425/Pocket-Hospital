package com.example.pockethospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pockethospital.Adapters.Adapter;
import com.example.pockethospital.Features.About;
import com.example.pockethospital.Features.Ambulance;
import com.example.pockethospital.Features.BloodDonation;
import com.example.pockethospital.Features.Oxygen_Cylinder;
import com.example.pockethospital.Features.Share;
import com.example.pockethospital.Features.mobileProfile;
import com.example.pockethospital.Features.profile;
import com.example.pockethospital.Login.LoginActivity;
import com.example.pockethospital.Models.slideImage;
import com.example.pockethospital.R;
import com.example.pockethospital.allFragments.Find_Medicine;
import com.example.pockethospital.allFragments.ForBed;
import com.example.pockethospital.allFragments.For_Fragments;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView nav;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    DatabaseReference myRef;
    Toolbar toolbar;
    Adapter adapter;
    String mobile_verfied,mobile_loggedIn;
    List<slideImage> helperList = new ArrayList<>();
    RecyclerView recyclerView;
    final static String KEY_NAME="mypref";
    final static String email_verified="false";
    final static String google_verified="false";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    slideImage helper = new slideImage();
                    String imageURL = dataSnapshot.getValue().toString();
                    helper.setImage(imageURL);
                    helperList.add(helper);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.event_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(), helperList);
        recyclerView.setAdapter(adapter);
        nav = findViewById(R.id.navmenu);
        nav.bringToFront();
        drawerLayout = findViewById(R.id.drawer);
        mAuth = FirebaseAuth.getInstance();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("slider");
        nav.setCheckedItem(R.id.menu_home);
        nav.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                    Intent i2 = new Intent(getApplicationContext(), mobileProfile.class);
                    i2.putExtra("number",sharedPreferences.getString("MobileNUmber"," "));
                    startActivity(i2);
                    nav.setCheckedItem(R.id.profile);
                }
                else {
                    Intent i2 = new Intent(getApplicationContext(), profile.class);
                    i2.putExtra("number",getIntent().getStringExtra("number"));
                    startActivity(i2);
                    nav.setCheckedItem(R.id.profile);
                }
                break;

            case R.id.menu_home:
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i1);
                nav.setCheckedItem(R.id.menu_home);
                finishAffinity();
                break;

            case R.id.menu_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91-8168196670"));
                startActivity(intent);
                nav.setCheckedItem(R.id.menu_call);
                break;

            case R.id.menu_about:
                Toast.makeText(getApplicationContext(), "About Section", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, About.class));
                nav.setCheckedItem(R.id.menu_about);
                break;
            case R.id.menu_Donate:
                Toast.makeText(getApplicationContext(), "Donate Section", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, donationActivity.class));
                nav.setCheckedItem(R.id.menu_Donate);
                break;
            case R.id.menu_logout:
                sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(google_verified,"false");
                editor.apply();
                mAuth.signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finishAffinity();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        nav.setCheckedItem(R.id.menu_home);
        sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("MobileNUmber",getIntent().getStringExtra("number"));
        editor.apply();
    }

    public void mealActivity(View view) {
        Toast.makeText(this, "Meals Activity", Toast.LENGTH_SHORT).show();
        Intent i =new Intent(this, For_Fragments.class);
        i.putExtra("KEY","Meals");
        startActivity(i);
    }

    public void findMedicine(View view) {
        Toast.makeText(this, "Find Medicine", Toast.LENGTH_SHORT).show();
        Intent i =new Intent(this, Find_Medicine.class);
        i.putExtra("KEY","Medicine");
        startActivity(i);

    }

    public void onlineDoc(View view) {
        Toast.makeText(this, "Online Doctor", Toast.LENGTH_SHORT).show();
        Intent i =new Intent(this,For_Fragments.class);
        i.putExtra("KEY","OnlineDoc");
        startActivity(i);
    }

    public void oxygen(View view) {
        Toast.makeText(this, "Oxygen", Toast.LENGTH_SHORT).show();
        Intent i =new Intent(this, Oxygen_Cylinder.class);
        i.putExtra("KEY","Oxygen");
        startActivity(i);
    }




    public void ShareCare(View view) {
        Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        Intent i =new Intent(this, Share.class);
        i.putExtra("KEY","Share");
        startActivity(i);
    }

    public void ForBed(View view) {
        Toast.makeText(this, "Covid Bed", Toast.LENGTH_SHORT).show();
        Intent i =new Intent(this, ForBed.class);
        i.putExtra("KEY","Covid Bed");
        startActivity(i);
    }

    public void ambulance(View view) {
        Toast.makeText(this, "Ambulance", Toast.LENGTH_SHORT).show();
        Intent i =new Intent(this, Ambulance.class);
        i.putExtra("KEY","Share");
        startActivity(i);
    }

    public void BloodDonation(View view) {
        Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        Intent i =new Intent(this, BloodDonation.class);
        i.putExtra("KEY","Share");
        startActivity(i);
    }
}
