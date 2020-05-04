package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button logout;
    TextView name,phone,mail,password;
    String mailid="";
    String uname="",umail="",upass="";
    Long uphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logout=(Button) findViewById(R.id.logout);
        name=(TextView)findViewById(R.id.name);
        phone=(TextView)findViewById(R.id.phone);
        mail=(TextView)findViewById(R.id.email);

        password=(TextView)findViewById(R.id.password);
        SharedPreferences sp=getSharedPreferences("user",0);
        mailid= sp.getString("Email","");
//        Toast.makeText(this,"\nmailid \t"+mailid,Toast.LENGTH_LONG).show();

//        List<StoreData> user=MainActivity.userData.myDoa().getUsers(mailid);
//                String info="";
//                for(StoreData data: user){
//                    String name = data.getName();
//                    String mail=data.getMail();
//                    String password=data.getPassword();
//                    Long phone=data.getPhone();
//                    info  +=name+"\n"+mail+"\n"+password+"\n"+phone;
//                    Toast.makeText(this,info,Toast.LENGTH_LONG).show();
//                }

        List<StoreData> userinfo=MainActivity.userData.myDoa().getUsers(mailid);
        for(StoreData user:userinfo){
            uname+=user.getName();
            uphone=user.getPhone();
            umail=user.getMail();
            upass=user.getPassword();
        }
        name.setText("Name:\t\t"+uname);
        phone.setText("Phone Number:\t\t"+uphone);
        mail.setText("Mail ID:\t\t"+mailid);
        password.setText("Password:\t\t"+upass);
        logout.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void display(){


    }
    @Override
    public void onClick(View view) {

        startActivity(new Intent(this,MainActivity.class));
    }
}
