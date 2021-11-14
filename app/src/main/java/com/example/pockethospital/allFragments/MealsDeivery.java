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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pockethospital.MainActivity;
import com.example.pockethospital.Models.MealsModel;
import com.example.pockethospital.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class MealsDeivery extends Fragment {

    View view;
    EditText Name, Number, Id;
    FirebaseFirestore db;
    Button button;
    RadioButton yes,no;
    String covid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_meals_deivery, container, false);

        db = FirebaseFirestore.getInstance();
        Name = view.findViewById(R.id.Recipent_Name);
        Number = view.findViewById(R.id.Recipent_Number);
        Id = view.findViewById(R.id.Recipent_Adrs);
        button = view.findViewById(R.id.Recipent_button);
        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RecipentName = Name.getText().toString();
                String RecipentNumber = Number.getText().toString();
                String RecipentAddress = Id.getText().toString();
                if(yes.isChecked()){
                    covid="yes";
                }
                else{
                    covid="no";
                }
                MealsModel meals = new MealsModel(RecipentName, RecipentNumber, RecipentAddress, covid);
                if(TextUtils.isEmpty(RecipentName) ||TextUtils.isEmpty(RecipentNumber) ||TextUtils.isEmpty(RecipentAddress)  )
                {
                    Toast.makeText(getContext(),"Please Fill The Details",Toast.LENGTH_SHORT).show();
                }
                else{
                    db.collection("Meals")
                            .add(meals)
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