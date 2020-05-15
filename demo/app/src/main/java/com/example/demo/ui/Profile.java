package com.example.demo.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Home;
import com.example.demo.MainActivity;
import com.example.demo.R;
import com.example.demo.StoreData;
import com.example.demo.UserPicture;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Profile extends AppCompatActivity implements View.OnClickListener {
private int STORAGE_CODE=1;
    TextView name,phone,mail,password;
    String mailid="";
    String uname="",umail="",upass="";
    Long uphone;
    ImageView picture;
    Uri imageuri;
    SharedPreferences sp;
    boolean hasPhoto=false;
    private static final int PICK_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=(TextView)findViewById(R.id.name);
        phone=(TextView)findViewById(R.id.phone);
        mail=(TextView)findViewById(R.id.email);
        picture=(ImageView)findViewById(R.id.profilePicture);
        picture.setOnClickListener(this);
        password=(TextView)findViewById(R.id.password);
        sp=getSharedPreferences("user",0);
        mailid= sp.getString("Email","");
        hasPhoto=MainActivity.userData.myDoa().hasPhoto(mailid);
        if(hasPhoto){
            List<UserPicture>userPictures =MainActivity.userData.myDoa().getPhoto(mailid);
            Bitmap image = null;
            for(UserPicture up: userPictures){

                image= BitmapFactory.decodeByteArray(up.getPicture(),0,up.getPicture().length);
            }
            picture.setImageBitmap(image);
//            Toast.makeText(getApplicationContext(),"select pic",Toast.LENGTH_LONG).show();
        }

        List<StoreData> userinfo= MainActivity.userData.myDoa().getUsers(mailid);
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

    }


    @Override
    public void onClick(View view) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            Intent galary=new Intent();
            galary.setType("image/*");
            galary.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(galary,"Select Picture"),PICK_IMAGE);

        }else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==STORAGE_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent galary=new Intent();
                galary.setType("image/*");
                galary.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galary,"Select Picture"),PICK_IMAGE);
            }
        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageuri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                picture.setImageBitmap(bitmap);
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,0,stream);
                byte[] image=stream.toByteArray();
                sp=getSharedPreferences("user",0);
                String mail=sp.getString("Email","");
                UserPicture up=new UserPicture();
                up.setEmail(mail);
                up.setPicture(image);
                if(hasPhoto){
                    MainActivity.userData.myDoa().updatePhoto(up);
                }else{
                    MainActivity.userData.myDoa().addPhoto(up);
                }
                Toast.makeText(this,"Profile photo updated",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,Home.class));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
