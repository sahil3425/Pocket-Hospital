package com.example.admin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.Models.MealsModel;
import com.example.admin.Models.MedicineModel;
import com.example.admin.R;
import com.example.admin.full_Medicine;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Medicine_Adapters extends RecyclerView.Adapter<Medicine_Adapters.Viewholder> {
    Context context;
    List<MedicineModel> helperList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Medicines");

    public Medicine_Adapters(Context context, List<MedicineModel> helperList) {
        this.context = context;
        this.helperList = helperList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medlayout, parent, false);
        return new Medicine_Adapters.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        MedicineModel helper = helperList.get(position);
        holder.Name.setText(helper.getPatientName());
        holder.Number.setText(helper.getPatientNumber());
        holder.Adress.setText(helper.getPatientAddress());
        Glide.with(context)
                .load(helperList.get(position).getImageUrl())
                .into(holder.imageView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),full_Medicine.class);
                intent.putExtra("image",helper.getImageUrl());
                v.getContext().startActivity(intent);
            }
        });
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + helper.getPatientNumber()));
                            v.getContext().startActivity(intent);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
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
        private TextView Name;
        private TextView Number;
        private TextView Adress;
        private LinearLayout layout;
        private ImageView imageView;
        private ImageButton callButton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.patientName);
            Number = itemView.findViewById(R.id.patientNumber);
            Adress = itemView.findViewById(R.id.patientAddress);
            imageView = itemView.findViewById(R.id.med_image);
            callButton = itemView.findViewById(R.id.btncall);
            layout = itemView.findViewById(R.id.linear);

        }
    }
}
