package com.example.pockethospital.Features;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pockethospital.Adapters.ShareAdapter;
import com.example.pockethospital.Models.ShareAndCare;
import com.example.pockethospital.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Share extends AppCompatActivity {
    RecyclerView recyclerView;
    ShareAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    List<ShareAndCare> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        recyclerView = findViewById(R.id.ShareRecycler);
        databaseReference = database.getInstance().getReference("ShareAbleItem");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList =new ArrayList<>();
        adapter = new ShareAdapter(getApplicationContext(),arrayList);
        recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   ShareAndCare s = new ShareAndCare();
                   s.setName("Name: "+dataSnapshot.child("name").getValue().toString());
                   s.setNumber("Number: "+dataSnapshot.child("number").getValue().toString());
                   s.setAddress("Address: "+dataSnapshot.child("address").getValue().toString());
                   s.setShareAbleItem("Item: "+dataSnapshot.child("shareAbleItem").getValue().toString());
                   arrayList.add(s);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}