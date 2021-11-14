package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.Models.BedModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bed extends AppCompatActivity {
TextView hospitalname,hospitalnumber,hospitalAdrs,bedavlbl;
Button upload;
String bedtype;
    private FirebaseAuth mAuth;
RadioButton icu,nonicu;
FirebaseDatabase BedList;
DatabaseReference dbref,dbref1;
    String mdid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed);
        BedList= FirebaseDatabase.getInstance();
        dbref= BedList.getReference("ICU");
        dbref1=BedList.getReference("Non ICU");
        hospitalname = findViewById(R.id.hsptl_Name);
        hospitalnumber = findViewById(R.id.hsptl_Number);
        mAuth = FirebaseAuth.getInstance();
        hospitalAdrs = findViewById(R.id.hsptl_Adrs);
        upload = findViewById(R.id.hsptl_button);
        bedavlbl = findViewById(R.id.Bed);
        icu=findViewById(R.id.icu);
        nonicu=findViewById(R.id.nonicu);

    }

    public void upload(View view) {
        if(TextUtils.isEmpty(hospitalname.getText().toString()) || TextUtils.isEmpty(hospitalAdrs.getText().toString()) || TextUtils.isEmpty(hospitalnumber.getText().toString())|| TextUtils.isEmpty(bedavlbl.getText().toString())){
            Toast.makeText(this, "Fill All The Data", Toast.LENGTH_SHORT).show();
            return ;
        }


            if(icu.isChecked()){
                bedtype="ICU";
                mdid = dbref.push().getKey();
                BedModel b = new BedModel(hospitalname.getText().toString(),hospitalnumber.getText().toString(),hospitalAdrs.getText().toString(),bedavlbl.getText().toString(),bedtype);
                dbref.child(mdid).setValue(b);
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                hospitalname.setText("");
                hospitalnumber.setText("");
                hospitalAdrs.setText("");
                bedavlbl.setText("");
                icu.setChecked(false);


            }
            else{
                bedtype="Non ICU";
                mdid = dbref1.push().getKey();
                BedModel b = new BedModel(hospitalname.getText().toString(),hospitalnumber.getText().toString(),hospitalAdrs.getText().toString(),bedavlbl.getText().toString(),bedtype);
                dbref1.child(mdid).setValue(b);
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                hospitalname.setText("");
                hospitalnumber.setText("");
                hospitalAdrs.setText("");
                bedavlbl.setText("");
                nonicu.setChecked(false);
            }





        }
    }
