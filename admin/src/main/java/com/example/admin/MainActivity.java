package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView nav;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void onlineDoc(View view) {
        startActivity(new Intent(this,online_Doctor.class));
    }

    public void oxygen(View view) {
        startActivity(new Intent(this,Oxygen.class));
    }

    public void findMedicine(View view) {
        startActivity(new Intent(this,find_Meadicine.class));
    }

    public void mealActivity(View view) {
        startActivity(new Intent(this,meals_Activity.class));
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav = findViewById(R.id.navmenu);
        nav.bringToFront();
        drawerLayout = findViewById(R.id.drawer);
        mAuth = FirebaseAuth.getInstance();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        database = FirebaseDatabase.getInstance();
        nav.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(getApplicationContext(),LoginAdmin.class));
                finish();
                break;
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(),shareActivity.class));
                nav.setCheckedItem(R.id.profile);
                break;
            case R.id.Home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        nav.setCheckedItem(R.id.Home);
    }


    public void ForBed(View view) {
        startActivity(new Intent(this,Bed.class));

    }

    public void ambulance(View view) {
        startActivity(new Intent(this,Ambulance.class));
    }

    public void BloodDonation(View view) {
        startActivity(new Intent(this,Bgroup.class));
    }

    public void ShareCare(View view) {
        startActivity(new Intent(getApplicationContext(),shareActivity.class));
    }
}