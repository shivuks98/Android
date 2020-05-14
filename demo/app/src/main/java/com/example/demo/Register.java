package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button register;
    EditText name,phone,email,password;
    SharedPreferences store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        store=getSharedPreferences("UserData",0);
        name=(EditText)findViewById(R.id.username);
        name.setHint("Enter Name");
        phone=(EditText)findViewById(R.id.phone);
        phone.setHint("Phone number");
        email=(EditText)findViewById(R.id.mail);
        email.setHint("Email");
        password=(EditText)findViewById(R.id.password);
        password.setHint("Password");
        register=(Button)findViewById(R.id.button2);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String username=name.getText().toString();
        Long phoneNo=12345678987654321L ;

        String mail = email.getText().toString();
        String pass = password.getText().toString();
        if(phone.getText().toString().length()!=0){
            phoneNo=Long.parseLong(phone.getText().toString());
        }
        Boolean hasPhone=MainActivity.userData.myDoa().hasPhone(phoneNo);
        Boolean hasMail=MainActivity.userData.myDoa().hasMail(mail);
        if(username.length()==0 ||  mail.length()==0 || pass.length()==0 || phoneNo==12345678987654321L){
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show();
        }
        else if(hasMail){
            Toast.makeText(this,"This Email is already registered "+mail,Toast.LENGTH_LONG).show();
        }
        else if(hasPhone){
            Toast.makeText(this,"This Phone number is already registered",Toast.LENGTH_LONG).show();
        }
        else{
//            Toast.makeText(this,"new user",Toast.LENGTH_LONG).show();
            StoreData data=new StoreData();
            data.setName(username);
            data.setMail(mail);
            data.setPassword(pass);
            data.setPhone(phoneNo);
            MainActivity.userData.myDoa().insert(data);
            Toast.makeText(this,"Registered Successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,MainActivity.class));
        }




//        SharedPreferences.Editor edit=store.edit();
//        edit.putString("Name",username);
//        edit.putLong("phone",phoneNo);
//        edit.putString("mail",mail);
//        edit.putString("password",pass);
//        edit.apply();

    }
}
