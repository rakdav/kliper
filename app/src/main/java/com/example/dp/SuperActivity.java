package com.example.dp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dp.Model.House;
import com.example.dp.View.FavoriteFragment;
import com.example.dp.View.HomeFragment;
import com.example.dp.View.SearchFragment;

import java.util.ArrayList;


public class SuperActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment=new HomeFragment();
                    fragmentTransaction.replace(R.id.container,new HomeFragment()).commit();
                    break;

                case R.id.navigation_dashboard:
                    fragment=new SearchFragment();
                    fragmentTransaction.replace(R.id.container,new SearchFragment()).commit();
                    break;
                case R.id.navigation_notifications:
                    fragment=new FavoriteFragment();
                    fragmentTransaction.replace(R.id.container,new FavoriteFragment()).commit();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super);
        BottomNavigationView navView = findViewById(R.id.navigation);
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,new HomeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("Клипер");
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;

    }




    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_tools :
                Intent intent=new Intent(this,SettingsActivity.class);
                startActivity(intent);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


}
