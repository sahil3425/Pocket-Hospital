package com.example.pockethospital.allFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pockethospital.MainActivity;
import com.example.pockethospital.Models.PatientModel;
import com.example.pockethospital.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class OnlineDoctor extends Fragment {
    View view;
    EditText Name, Number, Id, Complaint;
    FirebaseFirestore db;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_online_doctor, container, false);
        db = FirebaseFirestore.getInstance();
        Name = view.findViewById(R.id.patientName);
        Number = view.findViewById(R.id.patientNumber);
        Id = view.findViewById(R.id.patientId);
        button = view.findViewById(R.id.next_button);
        Complaint = view.findViewById(R.id.patientComplaint);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PatientName = Name.getText().toString();
                String PatientNumber = Number.getText().toString();
                String Patientid = Id.getText().toString();
                String PatientComplaint = Complaint.getText().toString();
                PatientModel patientModel = new PatientModel(PatientName, PatientNumber, Patientid, PatientComplaint);
                if(TextUtils.isEmpty(PatientName) ||TextUtils.isEmpty(PatientNumber) ||TextUtils.isEmpty(Patientid) ||TextUtils.isEmpty(PatientComplaint ))
                {
                    Toast.makeText(getContext(),"Please Fill The Details",Toast.LENGTH_SHORT).show();
                }
                else{
                    db.collection("users")
                            .add(patientModel)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getContext(),"Done",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                                    getActivity().finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
        });

        return view;
    }
}