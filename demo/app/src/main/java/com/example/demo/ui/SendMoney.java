package com.example.demo.ui;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.demo.Transactions;

public class SendMoney extends AppCompatActivity implements View.OnClickListener {
EditText phone,money;
Button send;
Long phoneNumber;
Double amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        phone=findViewById(R.id.phone);
        money=findViewById(R.id.amount);
        send=findViewById(R.id.send);
        phone.setHint("Enter Phone Number");
        money.setHint("Enter Amount");
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sp=getSharedPreferences("user",0);
        String mail=sp.getString("Email","");
        Long userphone=sp.getLong("Phone",0);
//        Long userphone=Long.parseLong(sp.getString("Phone",""));
        phoneNumber=Long.parseLong(phone.getText().toString());
        amount=Double.parseDouble(money.getText().toString());
        Double balance= MainActivity.userData.myDoa().getWalletAmount(mail);
        Boolean hasPhone =MainActivity.userData.myDoa().hasPhone(phoneNumber);
        if(balance<amount){
            Toast.makeText(this,"Insufficeint balace in your Wallet",Toast.LENGTH_LONG).show();
        }
        else if(!hasPhone){
            Toast.makeText(this,"Phone number doest not exists",Toast.LENGTH_LONG).show();
        }else {


            MainActivity.userData.myDoa().sendMoney(phoneNumber, amount);
            MainActivity.userData.myDoa().debitMoney(mail, amount);

//        p,e,d,a
            String Descption1 = "Amount Sent to " + phoneNumber;
            String Descrition2 = "Amount credited to your Wallet by " + userphone;

//        Sender Side Updation
            Transactions t1 = new Transactions();
            t1.setPhoneNumber(userphone);
            t1.setEmail(mail);
            t1.setDescription(Descption1);
            t1.setAmount(amount);
            MainActivity.userData.myDoa().addTransaction(t1);

//        Reciever Side Updation
            Transactions t2 = new Transactions();
            t2.setPhoneNumber(phoneNumber);
            t2.setDescription(Descrition2);
            t2.setEmail("");
            t2.setAmount(amount);
            MainActivity.userData.myDoa().addTransaction(t2);


            Toast.makeText(this, "sending money", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Home.class));
        }
    }
}
