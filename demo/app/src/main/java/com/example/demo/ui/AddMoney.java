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

public class AddMoney extends AppCompatActivity implements View.OnClickListener {
    Button addButton;
    EditText amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        addButton=(Button)findViewById(R.id.addButton);
        amount=(EditText)findViewById(R.id.money);
        amount.setHint("Enter the amount");
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences sp=getSharedPreferences("user",0);
        String mail=sp.getString("Email","");
        Long phone=sp.getLong("Phone",0);
        Double money=null;
        if(amount.getText().length()!=0){
        money=Double.parseDouble(amount.getText().toString());
        }
//        Toast.makeText(this,"Amount"+money ,Toast.LENGTH_LONG).show();
        if(money==null){
            Toast.makeText(this,"Enter the Amount",Toast.LENGTH_LONG).show();
        }
        else {
//            Toast.makeText(this,"the Amount is "+money,Toast.LENGTH_LONG).show();
            Transactions transactions = new Transactions();

            MainActivity.userData.myDoa().addMoney(money, mail);
            transactions.setAmount(money);
            transactions.setDescription("Amount Added to wallet");
            transactions.setEmail(mail);
            transactions.setPhoneNumber(phone);

            MainActivity.userData.myDoa().addTransaction(transactions);
            Toast.makeText(this,"Amount added Successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Home.class));
        }
    }
}
