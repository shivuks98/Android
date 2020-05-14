package com.example.demo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.Address;
import com.example.demo.Home;
import com.example.demo.MainActivity;
import com.example.demo.R;

public class ChangeAddress extends AppCompatActivity implements View.OnClickListener {
    EditText area,street,city,state;
    Button addAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        area=findViewById(R.id.Area);
        area.setHint("Area");
        street=findViewById(R.id.street);
        street.setHint("Street");
        city=findViewById(R.id.city);
        city.setHint("City");
        state=findViewById(R.id.state);
        state.setHint("State");
        addAddress=findViewById(R.id.address);
        addAddress.setText("Change Address");
        addAddress.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
//        Address address=new Address();
        SharedPreferences sp=getSharedPreferences("user",0);
        String mail=sp.getString("Email","");
        String uarea=area.getText().toString();
        String ustreet=street.getText().toString();
        String ucity= city.getText().toString();
        String ustate= state.getText().toString();
        if(uarea.length()==0 || ucity.length()==0||ustreet.length()==0||ustate.length()==0){
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show();
        }
        else {
            Address address = new Address();
            address.setArea(uarea);
            address.setStreet(ustreet);
            address.setCity(ucity);
            address.setState(ustate);
            address.setMail(mail);
            MainActivity.userData.myDoa().updateAddress(address);
            Toast.makeText(this, "Address Updated", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, DisplayAddress.class));
        }
    }
}
