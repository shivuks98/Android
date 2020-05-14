package com.example.demo.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.demo.R;

public class HomeFragment extends Fragment implements View.OnClickListener {
    CardView recharge,send;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        recharge=view.findViewById(R.id.recharge);
        send=view.findViewById(R.id.sendToContact);
//
        recharge.setOnClickListener(this);
        send.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendToContact:
                startActivity(new Intent(getContext(),SendMoney.class));
//                Toast.makeText(getActivity(),"send money",Toast.LENGTH_LONG).show();
                break;
            case R.id.recharge:

                Toast.makeText(getActivity(),"Recharge",Toast.LENGTH_LONG).show();
                break;
        }

    }
}
