package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.Models.OxygenModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Oxygen extends AppCompatActivity {
    TextView name,number,adrs,oxy_left,oxy_avail;
    FirebaseFirestore db;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen);
        name = findViewById(R.id.Company_Name);
        number = findViewById(R.id.Company_Number);
        adrs = findViewById(R.id.Company_Adrs);
        oxy_avail = findViewById(R.id.CylenderAvail);
        oxy_left = findViewById(R.id.CylenderLeft);
        db = FirebaseFirestore.getInstance();
        button =findViewById(R.id.Recipent_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RecipentName = name.getText().toString();
                String RecipentNumber = number.getText().toString();
                String RecipentAddress = adrs.getText().toString();
                String totalUnits = oxy_avail.getText().toString();
                String unitsLeft = oxy_left.getText().toString();

                OxygenModel model = new OxygenModel(RecipentName, RecipentNumber, RecipentAddress, totalUnits,unitsLeft);
                if(TextUtils.isEmpty(RecipentName) ||TextUtils.isEmpty(RecipentNumber) ||TextUtils.isEmpty(RecipentAddress)  )
                {
                    Toast.makeText(Oxygen.this,"Please Fill The Details",Toast.LENGTH_SHORT).show();
                }
                else{
                    db.collection("OxygenDetails")
                            .add(model)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Oxygen.this,"Failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
        });
    }
}