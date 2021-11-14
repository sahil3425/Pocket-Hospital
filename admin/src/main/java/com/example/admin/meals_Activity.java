package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.admin.Adapters.MealsAdapter;
import com.example.admin.Models.MealsModel;
import com.example.admin.Models.PatientModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class meals_Activity extends AppCompatActivity {
    MealsAdapter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("Meals");
    List<MealsModel> helperList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_);
        recyclerView = findViewById(R.id.meals_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MealsAdapter(getApplicationContext(), helperList);
        recyclerView.setAdapter(adapter);
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot queryDocumentSnapshot1 : queryDocumentSnapshots) {

                    MealsModel helper = new MealsModel();
                    helper.setName("Name : " + queryDocumentSnapshot1.getString("name"));
                    helper.setNumber("+91-" + queryDocumentSnapshot1.getString("number"));
                    helper.setAddress("Address : " + queryDocumentSnapshot1.getString("address"));
                    helper.setCovid("Covid : " + queryDocumentSnapshot1.getString("covid"));
                    helperList.add(helper);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}