package com.example.pockethospital.allFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.pockethospital.R;

public class ForBed extends AppCompatActivity {
    View v1, v2;
    public static String bedRefer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_bed);
        v1 = findViewById(R.id.Cdivider1);
        v2 = findViewById(R.id.Ndivider2);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentshow, fragment);
        fragmentTransaction.commit();

    }

    public void ClickCovid(View view) {
        replaceFragment(new CovidBed());
        v2.setVisibility(View.INVISIBLE);
        v1.setVisibility(View.VISIBLE);
        bedRefer="ICU";
    }

    public void NonClickCovid(View view) {
        replaceFragment(new NonICU());
        v2.setVisibility(View.VISIBLE);
        v1.setVisibility(View.INVISIBLE);
        bedRefer="Non ICU";
    }

    @Override
    protected void onStart() {

        super.onStart();
    }
}