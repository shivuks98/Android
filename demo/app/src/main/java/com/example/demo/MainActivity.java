package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button login,display;
    EditText username;
    EditText password;
    TextView register,view;

    public static useData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this,Login.class));
        login=(Button) findViewById(R.id.button);

        username =(EditText) findViewById(R.id.username);
        username.setHint("Enter email");
        password=(EditText) findViewById(R.id.password);
        password.setHint("Password");
        register=(TextView)findViewById(R.id.register1);
//        view=(TextView)findViewById(R.id.view);
//        display=(Button)findViewById(R.id.display);

    userData= Room.databaseBuilder(getApplicationContext(),useData.class,"Users").allowMainThreadQueries().build();

//        display.setOnClickListener(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
//        startActivity(new Intent(this,Register.class));
        switch (v.getId()){
            case R.id.button:
                String names=username.getText().toString();
                String pass=password.getText().toString();
                List<StoreData> getLogin=MainActivity.userData.myDoa().getUsers(names);
                String upass="",umail="";
                Long uphone=12345678910L;
                for(StoreData data :getLogin){
                    uphone=data.getPhone();
                    umail+=data.getMail();
                    upass+=data.getPassword();
                }
                SharedPreferences sp=getSharedPreferences("user",0);
                SharedPreferences.Editor spedit=sp.edit();
                spedit.putString("Email",umail);
                spedit.putLong("Phone",uphone);
                spedit.apply();
//                Toast.makeText(this,sp.getString("Email",""),Toast.LENGTH_LONG).show();
//                view.setText("Name:"+names+"\t"+uname+"\npass"+pass+"\t"+upass);
                if(names.length()==0 || pass.length()==0){
                    Toast.makeText(this,"Enter the details",Toast.LENGTH_LONG).show();
                }
                else if(umail.equals(names) && upass.equals(pass) )
                    startActivity(new Intent(this,Home.class));
                else
                    Toast.makeText(this,"Incorrect login details",Toast.LENGTH_LONG).show();
                break;
            case R.id.register1:
                startActivity(new Intent(this,Register.class));
                break;
//            case R.id.display:
//                List<StoreData> user=MainActivity.userData.myDoa().getUsers("aaa");
//                String info="";
//                for(StoreData data: user){
//                    String name = data.getName();
//                    String mail=data.getMail();
//                    String password=data.getPassword();
//                    Long phone=data.getPhone();
//                    info  +=name+"\n"+mail+"\n"+password+"\n"+phone;
//                }
//                view.setText(info);
////                SharedPreferences sp=getSharedPreferences("UserData",0);
////                String name=sp.getString("Name","");
////                String mail=sp.getString("mail","");
////                view.setText(name+"  "+ mail);
//                break;
        }
        //you need to start a new activity on this button click
        //run the code
//        Intent i = new Intent(this, Login.class);
//        startActivity(i);
    }
}
