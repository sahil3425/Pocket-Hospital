package com.example.pockethospital.Features;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pockethospital.Login.LoginActivity;
import com.example.pockethospital.MainActivity;
import com.example.pockethospital.R;
import com.example.pockethospital.donationActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class mobileProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView name,email;
    private CircleImageView imageView;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    private  FirebaseAuth mAuth;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    final static String KEY_NAME="mypref";
    final static String google_verified="false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.name);
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        imageView = findViewById(R.id.imagecircle);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav = findViewById(R.id.navmenu);
        nav.bringToFront();
        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setCheckedItem(R.id.menu_home);
        nav.setNavigationItemSelectedListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        nav.setCheckedItem(R.id.profile);
        name.setText("Number: "+mAuth.getCurrentUser().getPhoneNumber());
        email.setVisibility(View.GONE);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { switch (item.getItemId()) {
        case R.id.profile:
            Toast.makeText(getApplicationContext(),"You are already in Profile",Toast.LENGTH_SHORT).show();
            break;

        case R.id.menu_home:
            Toast.makeText(getApplicationContext(), "Home Section", Toast.LENGTH_LONG).show();
            startActivity(new Intent(mobileProfile.this, MainActivity.class));
            nav.setCheckedItem(R.id.menu_home);
            break;

        case R.id.menu_call:
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+91-8168196670"));
            startActivity(intent);
            nav.setCheckedItem(R.id.menu_call);
            break;

        case R.id.menu_about:
            Toast.makeText(getApplicationContext(), "About Section", Toast.LENGTH_LONG).show();
            startActivity(new Intent(mobileProfile.this,About.class));
            nav.setCheckedItem(R.id.menu_about);
            break;
        case R.id.menu_Donate:
            Toast.makeText(getApplicationContext(), "Donate Section", Toast.LENGTH_LONG).show();
            startActivity(new Intent(mobileProfile.this, donationActivity.class));
            nav.setCheckedItem(R.id.menu_Donate);
            break;
        case R.id.menu_logout:
            sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(google_verified,"false");
            editor.apply();
            mAuth.signOut();
            Intent i = new Intent(mobileProfile.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finishAffinity();
            break;

    }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
