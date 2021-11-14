package com.example.pockethospital.Features;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pockethospital.Login.LoginActivity;
import com.example.pockethospital.MainActivity;
import com.example.pockethospital.R;
import com.example.pockethospital.donationActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    TextView name, email;
    private CircleImageView imageView;
    private GoogleSignInOptions gso;
    private GoogleApiClient googleApiClient;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    final static String KEY_NAME = "mypref";
    final static String google_verified = "false";
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        nav.setCheckedItem(R.id.profile);
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleresult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleresult(result);
                }
            });
        }
    }

    @SuppressLint("ResourceType")
    private void handleresult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            name.setText("Name : " + account.getDisplayName());
            email.setText("Email : \n" + account.getEmail());

            Picasso.get().load(account.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(imageView);
        } else {

        }

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "You are already in Profile", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_home:
                Toast.makeText(getApplicationContext(), "Home Section", Toast.LENGTH_LONG).show();
                startActivity(new Intent(profile.this, MainActivity.class));
                nav.setCheckedItem(R.id.menu_home);
                break;

            case R.id.menu_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91-8168196670"));
                startActivity(intent);
                nav.setCheckedItem(R.id.menu_call);
                break;
            case R.id.menu_Donate:
                Toast.makeText(getApplicationContext(), "Donate Section", Toast.LENGTH_LONG).show();
                startActivity(new Intent(profile.this, donationActivity.class));
                nav.setCheckedItem(R.id.menu_Donate);
                break;
            case R.id.menu_about:
                Toast.makeText(getApplicationContext(), "About Section", Toast.LENGTH_LONG).show();
                startActivity(new Intent(profile.this, About.class));
                nav.setCheckedItem(R.id.menu_about);
                break;
            case R.id.menu_logout:
                sharedPreferences = getSharedPreferences(KEY_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(google_verified, "false");
                editor.apply();
                mAuth.signOut();
                Intent i = new Intent(profile.this, LoginActivity.class);
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
