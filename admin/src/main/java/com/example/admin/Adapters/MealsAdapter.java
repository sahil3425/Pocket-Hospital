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

import com.example.admin.Models.MealsModel;
import com.example.admin.Models.PatientModel;
import com.example.admin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.Viewholder> {
    Context context;
    List<MealsModel> helperList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference=db.collection("Meals");

    public MealsAdapter(Context context, List<MealsModel> helperList) {
        this.context = context;
        this.helperList = helperList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mealslayout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        MealsModel helper = helperList.get(position);
        holder.Name.setText(helper.getName());
        holder.Number.setText(helper.getNumber());
        holder.Adress.setText(helper.getAddress());
        holder.Adress.setText(helper.getAddress());
        holder.Covid.setText(helper.getCovid());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot queryDocumentSnapshot1:queryDocumentSnapshots)
                        {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:"+helper.getNumber()));
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

        private TextView Name;
        private TextView Number;
        private TextView Adress;
        private TextView Covid;
        private ImageButton callButton;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.name);
            Number=itemView.findViewById(R.id.number);
            Adress=itemView.findViewById(R.id.address);
            Covid=itemView.findViewById(R.id.covid);
            callButton=itemView.findViewById(R.id.btncall);

        }
    }
}
