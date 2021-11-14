package com.example.pockethospital.Adapters;

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

import com.example.pockethospital.Models.oxygenModels;
import com.example.pockethospital.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class oxygenCylinderAdapter extends RecyclerView.Adapter<oxygenCylinderAdapter.ViewHolder>  {
    Context context;
    List<oxygenModels> helperList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference=db.collection("OxygenDetails");
    public oxygenCylinderAdapter(Context context, List<oxygenModels> helperList) {
        this.context = context;
        this.helperList = helperList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buylayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        oxygenModels helper = helperList.get(position);
        holder.companyName.setText(helper.getCompanyName());
        holder.companyNumber.setText(helper.getCompanyNumber());
        holder.companyAddress.setText(helper.getCompanyAddress());
        holder.totalUnit.setText(helper.getTotalUnits());
        holder.unitsLeft.setText(helper.getUnitsleft());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot queryDocumentSnapshot1:queryDocumentSnapshots)
                        {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:"+helper.getCompanyNumber()));
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


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView companyName;
        private TextView companyNumber;
        private TextView companyAddress;
        private TextView totalUnit;
        private TextView unitsLeft;
        private ImageButton callButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyName=itemView.findViewById(R.id.companyName);
            companyNumber=itemView.findViewById(R.id.companyNumber);
            companyAddress=itemView.findViewById(R.id.companyAddress);
            totalUnit=itemView.findViewById(R.id.totalUnits);
            unitsLeft=itemView.findViewById(R.id.unitsLeft);
            callButton=itemView.findViewById(R.id.btncall);

        }
    }


}
