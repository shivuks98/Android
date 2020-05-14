package com.example.demo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.Home;
import com.example.demo.MainActivity;
import com.example.demo.R;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {
EditText pass1,pass2,pass3;
Button change;
SharedPreferences sp;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        sp= getSharedPreferences("user",0);

        pass1=findViewById(R.id.oldPassword);
        pass2=findViewById(R.id.newPassword);
        pass3=findViewById(R.id.newPassword1);
        pass1.setHint("Old Password");
        pass2.setHint("New Password");
        pass3.setHint("Confirm Password");
        change=findViewById(R.id.changePassword);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String mail=sp.getString("Email","");
        String oldpass=pass1.getText().toString();
        String newpass=pass2.getText().toString();
        String cpass=pass3.getText().toString();
        String getOldPass= MainActivity.userData.myDoa().getPassword(mail);
        if(oldpass.length()==0|| newpass.length()==0 || cpass.length()==0){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_LONG).show();
        }
        else if(!newpass.equals(cpass)){
            Toast.makeText(this,"new password and Confirm password not matched",Toast.LENGTH_LONG).show();
        }
        else if(getOldPass.equals(oldpass)){
            MainActivity.userData.myDoa().updatePassword(newpass,mail);
            Toast.makeText(this,"Password Changed successfully",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//            startActivity(new Intent(this, Home.class));
//
        }
        else {
            Toast.makeText(this," Old password Does not matched",Toast.LENGTH_LONG).show();
        }


    }
}
