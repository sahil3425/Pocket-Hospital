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

import com.example.pockethospital.Models.BedModel;
import com.example.pockethospital.R;
import com.example.pockethospital.allFragments.ForBed;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class BedAdapter extends RecyclerView.Adapter<BedAdapter.MyViewHolder> {
    Context context;
    List<BedModel> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(ForBed.bedRefer);

    public BedAdapter(Context context, List arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.getlayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BedModel b = (BedModel) arrayList.get(position);
        holder.hname.setText(b.getHsname());
        holder.hnumber.setText(b.getHsnmbr());
        holder.hadrs.setText(b.getHsadrs());
        holder.bedavl.setText(b.getBdavl());
        holder.bedtype.setText(b.getBdtype());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + "+91-"+b.getHsnmbr().substring(b.getHsnmbr().indexOf(":")+1)));
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
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView hname,hnumber,hadrs,bedavl,bedtype;
        private ImageButton callButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hname  = itemView.findViewById(R.id.hospitalName);
            hnumber  = itemView.findViewById(R.id.hospitalNumber);
            hadrs  = itemView.findViewById(R.id.hospitalAdrs);
            bedavl = itemView.findViewById(R.id.BedAvailable);
            bedtype = itemView.findViewById(R.id.BedType);
            callButton = itemView.findViewById(R.id.callHospital);

        }
    }
}
