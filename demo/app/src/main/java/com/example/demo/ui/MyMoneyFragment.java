package com.example.demo.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.demo.MainActivity;
import com.example.demo.R;
import com.example.demo.StoreData;
import com.example.demo.useData;

import java.util.List;

public class MyMoneyFragment extends Fragment implements View.OnClickListener {
    public static useData getbalance;
    TextView wallet;
     CardView addmoney;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mymoney, container, false);
        wallet=inflate.findViewById(R.id.balance);
        addmoney=inflate.findViewById(R.id.addMoney);
        addmoney.setOnClickListener(this);
         SharedPreferences sp=getActivity().getSharedPreferences("user",0);
         String mail=sp.getString("Email","");
        Double balance= MainActivity.userData.myDoa().getWalletAmount(mail);
        wallet.setText(balance+"");
        return inflate;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(),AddMoney.class));
    }
}
