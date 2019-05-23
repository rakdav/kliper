package com.example.dp;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.dp.Model.House;
import com.example.dp.View.FavoriteFragment;
import com.example.dp.View.HomeFragment;
import com.example.dp.View.InfoFragment;
import com.example.dp.View.SearchFragment;

import java.util.ArrayList;


public class SuperActivity extends AppCompatActivity  {
    private FragmentManager fragmentManager;
    private ActionBar toolbar;
    private MenuItem ItemClear;
    private MenuItem ItemMap;
    private FloatingActionButton fabSearch;
    private FloatingActionButton fabReturn;
    private boolean Map;
    private boolean Find;
    SearchView searchView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("RestrictedApi")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction.replace(R.id.container,new HomeFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Map=true;
                    Find=true;
                    toolbar.setTitle("Клипер");
                    invalidateOptionsMenu();
                    fabSearch.setVisibility(View.VISIBLE);
                    break;
                case R.id.navigation_main:
                    fragmentTransaction.replace(R.id.container,new InfoFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Клипер");
                    Map=false;
                    Find=true;
                    invalidateOptionsMenu();
                    fabSearch.setVisibility(View.INVISIBLE);
                    break;
                case R.id.navigation_notifications:
                    fragmentTransaction.replace(R.id.container,new FavoriteFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Map=true;
                    Find=true;
                    invalidateOptionsMenu();
                    toolbar.setTitle("Избранное");
                    fabSearch.setVisibility(View.INVISIBLE);
                    break;
            }
            return true;
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super);
        BottomNavigationView navView = findViewById(R.id.navigation);
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,new InfoFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Клипер");
        Map=true;
        fabSearch = (FloatingActionButton) findViewById(R.id.fabSearch);
        fabSearch.setVisibility(View.INVISIBLE);
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,new SearchFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                fabSearch.setVisibility(View.INVISIBLE);
                toolbar.setTitle("Поиск");
                Map=false;
                Find=true;
                invalidateOptionsMenu();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        ItemClear=menu.findItem(R.id.action_clear);
        ItemMap=menu.findItem(R.id.action_mapall);
        if(Map) ItemClear.setVisible(false);
        if(Find) ItemMap.setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_tools :

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
