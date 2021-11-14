package com.example.pockethospital.Features;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pockethospital.MainActivity;
import com.example.pockethospital.Models.BgroupModel;
import com.example.pockethospital.Models.PatientModel;
import com.example.pockethospital.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class BloodDonation extends AppCompatActivity {
    EditText Name, Number, Id, bgroup;
    FirebaseFirestore db;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donation);

        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PatientName = Name.getText().toString();
                String PatientNumber = Number.getText().toString();
                String Patientid = Id.getText().toString();
                String PatientComplaint = bgroup.getText().toString();
                BgroupModel patientModel = new BgroupModel(PatientName, PatientNumber, Patientid, PatientComplaint);
                if(TextUtils.isEmpty(PatientName) ||TextUtils.isEmpty(PatientNumber) ||TextUtils.isEmpty(Patientid) ||TextUtils.isEmpty(PatientComplaint ))
                {
                    Toast.makeText(getApplicationContext(),"Please Fill The Details",Toast.LENGTH_SHORT).show();
                }
                else{
                    db.collection("BloodDonation_Users")
                            .add(patientModel)
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
                                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
        });
    }
    private void init() {
        db = FirebaseFirestore.getInstance();
        Name = findViewById(R.id.patientName_bg);
        Number = findViewById(R.id.patientNumber_bg);
        Id =findViewById(R.id.patientId_bg);
        button = findViewById(R.id.next_button_bg);
        bgroup = findViewById(R.id.patient_blood_group);
    }
}