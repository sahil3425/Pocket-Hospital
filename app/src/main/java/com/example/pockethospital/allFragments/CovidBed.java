package com.example.pockethospital.allFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pockethospital.Adapters.BedAdapter;
import com.example.pockethospital.Models.BedModel;
import com.example.pockethospital.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CovidBed extends Fragment {
    RecyclerView recyclerView;
    BedAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    List<BedModel> arrayList;

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_covid_bed, container, false);

        recyclerView = v.findViewById(R.id.CovidRecycler);
        databaseReference = FirebaseDatabase.getInstance().getReference("ICU");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList =new ArrayList<>();

        adapter = new BedAdapter(getContext(),arrayList);
        recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                BedModel user = new BedModel();
                user.setBdavl("Bed Available: "+dataSnapshot.child("bedAvailable").getValue().toString());
                user.setBdtype("Bed Type: "+dataSnapshot.child("bedtype").getValue().toString());
                user.setHsadrs("Address: "+dataSnapshot.child("hospitalAdrs").getValue().toString());
                user.setHsname("Hospital Name: "+dataSnapshot.child("hospitalName").getValue().toString());
                user.setHsnmbr("Phone Number: "+dataSnapshot.child("hospitalNumber").getValue().toString());
                arrayList.add(user);

            }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return  v;
    }
}