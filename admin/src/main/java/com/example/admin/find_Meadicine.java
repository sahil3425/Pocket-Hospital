package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.Adapters.Medicine_Adapters;
import com.example.admin.Models.MealsModel;
import com.example.admin.Models.MedicineModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class find_Meadicine extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    Medicine_Adapters adapter;
    RecyclerView recyclerView;
    List<MedicineModel> helperList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__meadicine);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Medicines");
        recyclerView =findViewById(R.id.medicine_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new Medicine_Adapters(getApplicationContext(), helperList);
        recyclerView.setAdapter(adapter);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MedicineModel helper = new MedicineModel();
                    helper.setPatientName(dataSnapshot.child("patientName").getValue().toString());
                    helper.setPatientNumber("+91-" + dataSnapshot.child("patientNumber").getValue().toString());
                    helper.setPatientAddress("Address : " + dataSnapshot.child("patientAddress").getValue().toString());
                    helper.setImageUrl(dataSnapshot.child("imageUrl").getValue().toString());
                    helperList.add(helper);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Data Fetch Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}