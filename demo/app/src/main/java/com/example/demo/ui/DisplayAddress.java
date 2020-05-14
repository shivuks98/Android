package com.example.demo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.Address;
import com.example.demo.MainActivity;
import com.example.demo.R;

import java.util.List;

public class DisplayAddress extends AppCompatActivity implements View.OnClickListener {
TextView a,s,c,st;
String area="",street="",city="",state="";
SharedPreferences sp;
TextView change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_address);
        sp=getSharedPreferences("user",0);
        String mail=sp.getString("Email","");
        a=findViewById(R.id.dArea);
        s=findViewById(R.id.dstreet);
        c=findViewById(R.id.dcity);
        st=findViewById(R.id.dstate);
        change=findViewById(R.id.editAddress);
        change.setOnClickListener(this);

        List<Address> address= MainActivity.userData.myDoa().getAddress(mail);
        for(Address a:address){
            area+=a.getArea();
            street+=a.getStreet();
            city+=a.getCity();
            state+=a.getState();
        }
        a.setText("Area : "+area);
        s.setText("Street : "+street);
        c.setText("City : "+city);
        st.setText("State : "+state);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,ChangeAddress.class));
    }
}
