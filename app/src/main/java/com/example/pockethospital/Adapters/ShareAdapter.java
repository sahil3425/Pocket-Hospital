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

import com.example.pockethospital.Models.ShareAndCare;
import com.example.pockethospital.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.Viewholder> {
    Context context;
    List<ShareAndCare> helperList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ShareAbleItem");
    public ShareAdapter(Context context, List<ShareAndCare> helperList) {
        this.context = context;
        this.helperList = helperList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forshare, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ShareAndCare helper = helperList.get(position);
        holder.Name.setText(helper.getName());
        holder.Number.setText(helper.getNumber());
        holder.Adrs.setText(helper.getAddress());
        holder.Share.setText(helper.getShareAbleItem());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + "+91-"+helper.getNumber().substring(helper.getNumber().indexOf(":")+1)));
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

       private TextView Name,Number,Adrs,Share;
        private ImageButton callButton;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.SName);
            Number=itemView.findViewById(R.id.SNumber);
            Adrs=itemView.findViewById(R.id.SAdrs);
            Share=itemView.findViewById(R.id.SAlbetem);
            callButton = itemView.findViewById(R.id.calluser);
        }
    }
}
