package com.example.pockethospital.Features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.pockethospital.Adapters.oxygenCylinderAdapter;
import com.example.pockethospital.Models.oxygenModels;
import com.example.pockethospital.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Oxygen_Cylinder extends AppCompatActivity {
    oxygenCylinderAdapter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("OxygenDetails");
    List<oxygenModels> helperList = new ArrayList<>();
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_oxygen_cylender);
        recyclerView = findViewById(R.id.buyRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Circle();
        progressBar.setIndeterminateDrawable(doubleBounce);
        adapter = new oxygenCylinderAdapter(getApplicationContext(), helperList);
        recyclerView.setAdapter(adapter);
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot queryDocumentSnapshot1 : queryDocumentSnapshots) {

                    oxygenModels helper = new oxygenModels();
                    helper.setCompanyName("Name : " + queryDocumentSnapshot1.getString("name"));
                    helper.setCompanyNumber("+91-" + queryDocumentSnapshot1.getString("number"));
                    helper.setCompanyAddress("Address : " + queryDocumentSnapshot1.getString("address"));
                    helper.setTotalUnits("TotalUnits : " + queryDocumentSnapshot1.getString("totalUnits"));
                    helper.setUnitsleft("unitsLeft : " + queryDocumentSnapshot1.getString("unitsleft"));
                    helperList.add(helper);
                }
                adapter.notifyDataSetChanged();
            }
        });
        progressBar.setVisibility(View.GONE);

    }


}