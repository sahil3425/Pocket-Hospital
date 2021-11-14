package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.admin.Adapters.BloodGroupAdpter;
import com.example.admin.Adapters.DoctorAdapter;
import com.example.admin.Models.BgroupModel;
import com.example.admin.Models.PatientModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Bgroup extends AppCompatActivity {
    BloodGroupAdpter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference=db.collection("BloodDonation_Users");
    List<BgroupModel> helperList = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgroup);
        recyclerView =findViewById(R.id.buyRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new BloodGroupAdpter(getApplicationContext(), helperList);
        recyclerView.setAdapter(adapter);
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot queryDocumentSnapshot1:queryDocumentSnapshots)
                {

                    BgroupModel helper = new BgroupModel();
                    helper.setPatientName("Name : "+queryDocumentSnapshot1.getString("patientName"));
                    helper.setPatientNumber("+91-" + queryDocumentSnapshot1.getString("patientNumber"));
                    helper.setPatientId("ID : " + queryDocumentSnapshot1.getString("patientId"));
                    helper.setPatientgroup("Patient BloodGroup : " + queryDocumentSnapshot1.getString("patientgroup"));
                    helperList.add(helper);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}