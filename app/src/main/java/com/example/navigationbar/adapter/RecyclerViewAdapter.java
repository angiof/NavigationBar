package com.example.navigationbar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationbar.LeTueSchedePackage.Schede;
import com.example.navigationbar.R;
import com.example.navigationbar.views.SchedeFragment;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Schede> schedes;
    private Context context;

    public RecyclerViewAdapter(List<Schede> schedes, Context context) {
        this.schedes = schedes;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Schede schede = schedes.get(position);
        holder.title.setText(schede.getTitle());
        holder.text.setText(schede.getMessage());
        holder.date.setText(schede.getData());
        holder.touch_layout.setOnClickListener((view)->{
          SchedeFragment.sendObject(context,position);
          notifyItemChanged(holder.getAdapterPosition());

        });

    }

    @Override
    public int getItemCount() {
        return schedes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title,text,date;
        private ConstraintLayout touch_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            text= itemView.findViewById(R.id.text);
            date= itemView.findViewById(R.id.date);
            touch_layout=itemView.findViewById(R.id.touch_layout);
        }
    }
}
