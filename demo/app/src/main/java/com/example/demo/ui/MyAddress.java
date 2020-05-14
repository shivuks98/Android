package com.example.demo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo.MainActivity;
import com.example.demo.R;

public class MyAddress extends AppCompatActivity implements View.OnClickListener {
    Button add;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        add=findViewById(R.id.addAddress);
        add.setOnClickListener(this);
//        add(mail);
    }

  @Override
    public void onClick(View view) {
        startActivity(new Intent(this,AddAddress.class));
    }
}
