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
        Long phoneNo = Long.parseLong(phone.getText().toString());
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        StoreData data=new StoreData();
        data.setName(username);
        data.setMail(mail);
        data.setPassword(pass);
        data.setPhone(phoneNo);
MainActivity.userData.myDoa().insert(data);


//        SharedPreferences.Editor edit=store.edit();
//        edit.putString("Name",username);
//        edit.putLong("phone",phoneNo);
//        edit.putString("mail",mail);
//        edit.putString("password",pass);
//        edit.apply();
        Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();

    startActivity(new Intent(this,MainActivity.class));
    }
}
