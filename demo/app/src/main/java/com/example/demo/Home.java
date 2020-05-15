package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.ui.ChangePassword;
import com.example.demo.ui.DisplayAddress;
import com.example.demo.ui.History;
import com.example.demo.ui.HomeFragment;
import com.example.demo.ui.MyAddress;
import com.example.demo.ui.MyMoneyFragment;
import com.example.demo.ui.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawer;
SharedPreferences sp;
TextView name,mail;
ImageView pic;
boolean hasPhoto=false;
private static final int PICK_IMAGE=1;
Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);

        SharedPreferences sp=getSharedPreferences("user",0);
        String username=sp.getString("Name","");
        String usermail=sp.getString("Email","");

        NavigationView navigationView=findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        name=header.findViewById(R.id.loginname);
        mail=header.findViewById(R.id.loginmail);
        pic=header.findViewById(R.id.userpic);

        hasPhoto=MainActivity.userData.myDoa().hasPhoto(usermail);
        if(hasPhoto){
            List<UserPicture>userPictures =MainActivity.userData.myDoa().getPhoto(usermail);
             Bitmap image = null;
            for(UserPicture up: userPictures){
                image= BitmapFactory.decodeByteArray(up.getPicture(),0,up.getPicture().length);
            }
            pic.setImageBitmap(image);
//            Toast.makeText(getApplicationContext(),"select pic",Toast.LENGTH_LONG).show();
        }

        name.setText(username);
        mail.setText(usermail);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(listener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }
    private  BottomNavigationView.OnNavigationItemSelectedListener listener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    return false;
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            Toolbar t=findViewById(R.id.toolbar);
                            t.setTitle("Home");
                            selectedFragment=new HomeFragment();
                            break;
                        case R.id.nav_mymoney:
                            Toolbar t1=findViewById(R.id.toolbar);
                            t1.setTitle("My Money");
                            selectedFragment=new MyMoneyFragment();
                            break;
                        case R.id.nav_history:
                            Toolbar t2=findViewById(R.id.toolbar);
                            t2.setTitle("History");
                            selectedFragment=new History();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return  true;
                }
            };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:
                    startActivity(new Intent(this, Profile.class));
//                getSupportFragmentManager().beginTransaction().replace(R.id.full_frame,new HomeFragment()).commit();
                break;
            case R.id.changePassword:
                    startActivity(new Intent(this, ChangePassword.class));
//                getSupportFragmentManager().beginTransaction().replace(R.id.full_frame,new FavoritesFragment()).commit();
                break;
            case R.id.address:
                sp=getSharedPreferences("user",0);
                String mail=sp.getString("Email","");
                Boolean has= MainActivity.userData.myDoa().hasAddress(mail);
                if(has){
                    startActivity(new Intent(this, DisplayAddress.class));
                }else
                startActivity(new Intent(this, MyAddress.class));
                 break;
            case R.id.logout:
                Intent intent=new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
//                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
