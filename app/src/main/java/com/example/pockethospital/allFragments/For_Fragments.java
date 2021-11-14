package com.example.pockethospital.allFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.pockethospital.R;

public class For_Fragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for__fragments);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentshow, fragment);
        fragmentTransaction.commit();

    }
    @Override
    protected void onStart() {
        super.onStart();
        String value = getIntent().getExtras().getString("KEY");
        switchcase(value);

    }

    private void switchcase(String value) {
        switch (value) {
            case "OnlineDoc":
                replaceFragment(new OnlineDoctor());
                break;
            case "Meals":
                replaceFragment(new MealsDeivery());
                break;


        }
    }
}