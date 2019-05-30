package com.example.dp;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.dp.API.APIService;
import com.example.dp.API.APIUrl;
import com.example.dp.Controller.HouseAdapter;
import com.example.dp.Model.House;
import com.example.dp.Model.HouseList;
import com.example.dp.View.FavoriteFragment;
import com.example.dp.View.HomeFragment;
import com.example.dp.View.InfoFragment;
import com.example.dp.View.SearchFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SuperActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private ActionBar toolbar;
    private MenuItem ItemClear;
    private MenuItem ItemMap;
    private FloatingActionButton fabSearch;
    private boolean Map;
    private boolean Find;
    private boolean Sear;
    private ArrayList<House> houses;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_MAIN = "Main";
    public static final String APP_PREFERENCES_SEARCH = "Search";
    public static final String APP_PREFERENCES_FAVORITE = "Favorite";
    private SharedPreferences.Editor editor;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("RestrictedApi")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction.replace(R.id.container, new HomeFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Map = true;
                    Find = false;
                    Sear = false;
                    toolbar.setTitle("Поиск");
                    invalidateOptionsMenu();
                    fabSearch.setVisibility(View.VISIBLE);
                    break;

                case R.id.navigation_main:
                    fragmentTransaction.replace(R.id.container,new InfoFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    toolbar.setTitle("Клипер");
                    Map=true;
                    Find=true;
                    Sear=true;
                    ItemClear.setVisible(false);
                    invalidateOptionsMenu();
                    fabSearch.setVisibility(View.INVISIBLE);
                    break;
                case R.id.navigation_notifications:
                    fragmentTransaction.replace(R.id.container,new FavoriteFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Map=true;
                    Find=true;
                    Sear=true;
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
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        toolbar = getSupportActionBar();
            fragmentTransaction.add(R.id.container, new InfoFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Клипер");
        Map=true;
        Find=true;
        Sear=true;
        fabSearch = (FloatingActionButton) findViewById(R.id.fabSearch);
        fabSearch.setVisibility(View.INVISIBLE);
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SuperActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        houses=new ArrayList<>();
        Call<HouseList> call=service.getUserss();
        call.enqueue(new Callback<HouseList>() {
            @Override
            public void onResponse(Call<HouseList> call, Response<HouseList> response) {
                houses=response.body().getHouses();
            }

            @Override
            public void onFailure(Call<HouseList> call, Throwable t) {
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        ItemClear=menu.findItem(R.id.action_clear);
        ItemMap=menu.findItem(R.id.action_mapall);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        if(Map) ItemClear.setVisible(false);
        if(Find) ItemMap.setVisible(false);
        if(Sear) searchItem.setVisible(false);


        ItemMap.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent=new Intent(SuperActivity.this,MapsActivity2.class);
                intent.putExtra("houss", houses);
                startActivity(intent);
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
