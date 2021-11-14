package com.example.pockethospital.Features;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.pockethospital.MainActivity;
import com.example.pockethospital.R;
import com.example.pockethospital.donationActivity;
import com.google.android.material.navigation.NavigationView;

public class About extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://mayank816.github.io/covizoneAbout/");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
        case R.id.profile:
            Intent i2 = new Intent(getApplicationContext(), profile.class);
            startActivity(i2);
            nav.setCheckedItem(R.id.profile);
            break;

        case R.id.menu_home:
            Toast.makeText(getApplicationContext(), "About Section", Toast.LENGTH_LONG).show();
            startActivity(new Intent(About.this, MainActivity.class));
            nav.setCheckedItem(R.id.menu_about);
            break;

        case R.id.menu_call:
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+91-8168196670"));
            startActivity(intent);
            nav.setCheckedItem(R.id.menu_call);
            break;

            case R.id.menu_Donate:
                Toast.makeText(getApplicationContext(), "Donate Section", Toast.LENGTH_LONG).show();
                startActivity(new Intent(About.this, donationActivity.class));
                nav.setCheckedItem(R.id.menu_Donate);
                break;
            case R.id.menu_about:

            Toast.makeText(getApplicationContext(),"You are already in About",Toast.LENGTH_SHORT).show();
        break;

    }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        nav.setCheckedItem(R.id.menu_about);
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
