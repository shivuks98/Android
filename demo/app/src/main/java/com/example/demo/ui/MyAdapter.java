package com.example.demo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private  Context context;
    private  String description[];
    private Double amount[];

    public MyAdapter(Context context, String[] desc, Double[] amount){
        this.context=context;
        this.description=desc;
        this.amount=amount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.get_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.desc.setText(description[position]);
holder.amount.setText(amount[position]+"");
    }

    @Override
    public int getItemCount() {
        return description.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView desc,amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desc=itemView.findViewById(R.id.description);
            desc.setHint("No History");
            amount=itemView.findViewById(R.id.money);
        }
    }
}
