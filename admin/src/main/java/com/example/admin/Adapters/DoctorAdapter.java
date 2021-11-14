package com.example.admin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.Models.PatientModel;
import com.example.admin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.Viewholder> {
    Context context;
    List<PatientModel> helperList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference=db.collection("users");

    public DoctorAdapter(Context context, List<PatientModel> helperList) {
        this.context = context;
        this.helperList = helperList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buylayout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        PatientModel helper = helperList.get(position);
        holder.patientname.setText(helper.getPatientName());
        holder.patientnumber.setText(helper.getPatientNumber());
        holder.patientid.setText(helper.getPatientId());
        holder.patientcomplaint.setText(helper.getPatientComplaint());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot queryDocumentSnapshot1:queryDocumentSnapshots)
                        {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:"+helper.getPatientNumber()));
                            v.getContext().startActivity(intent);
                        }

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return helperList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        
        private TextView patientname;
        private TextView patientnumber;
        private TextView patientid;
        private TextView patientcomplaint;
        private ImageButton callButton;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            patientname=itemView.findViewById(R.id.patientName);
            patientnumber=itemView.findViewById(R.id.patientNumber);
            patientid=itemView.findViewById(R.id.patientId);
            patientcomplaint=itemView.findViewById(R.id.patientgrp);
            callButton=itemView.findViewById(R.id.btncall);

        }
    }
}
