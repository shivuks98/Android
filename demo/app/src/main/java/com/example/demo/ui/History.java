package com.example.demo.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.MainActivity;
import com.example.demo.R;
import com.example.demo.Transactions;

import java.util.List;

public class History extends Fragment {

    RecyclerView recyclerView;
//    int i=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_history,container,false);

        recyclerView=view.findViewById(R.id.recycleView);
//Toast.makeText(getContext(),sp.getString("Email",""),Toast.LENGTH_LONG).show();
        SharedPreferences sp=getActivity().getSharedPreferences("user",0);
        String mail=sp.getString("Email","");
        Long phone=sp.getLong("Phone",0);
        List<Transactions> history= MainActivity.userData.myDoa().getTransactions(phone);
        String desc[]=new String[history.size()];
        Double amount[] = new Double[history.size()];
        int i=history.size()-1;
        for(Transactions tr:history){
            desc[i]=tr.getDescription();
            amount[i]=tr.getAmount();
            i--;
        }
//        Toast.makeText(getContext(),desc,Toast.LENGTH_LONG).show();
        MyAdapter myAdapter=new MyAdapter(getContext(),desc,amount);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
