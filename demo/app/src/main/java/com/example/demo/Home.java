package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.demo.ui.ChangePassword;
import com.example.demo.ui.History;
import com.example.demo.ui.HomeFragment;
import com.example.demo.ui.MyAddress;
import com.example.demo.ui.MyMoneyFragment;
import com.example.demo.ui.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawer;
TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);

        SharedPreferences sp=getSharedPreferences("user",0);
        String username=sp.getString("Email","");

        NavigationView navigationView=findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        name=header.findViewById(R.id.loginname);
        name.setText(username);
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
                            selectedFragment=new HomeFragment();
                            break;
                        case R.id.nav_mymoney:
                            selectedFragment=new MyMoneyFragment();
                            break;
                        case R.id.nav_history:
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
                startActivity(new Intent(this, MyAddress.class));
                break;
            case R.id.logout:

                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
