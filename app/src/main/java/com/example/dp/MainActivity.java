package com.example.dp;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dp.API.APIService;
import com.example.dp.API.APIUrl;
import com.example.dp.Controller.HouseAdapter;
import com.example.dp.Model.House;
import com.example.dp.Model.HouseList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, HouseAdapter.HouseAdapterListener {
    private HouseAdapter adapter;
    private RecyclerView rv;
    private ArrayList<House> houses;
    SearchView searchView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().clear().apply();
        setContentView(R.layout.activity_main);
        rv=(RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        houses=new ArrayList<>();
        Call<HouseList> call=service.getUsers();
        call.enqueue(new Callback<HouseList>() {
            @Override
            public void onResponse(Call<HouseList> call, Response<HouseList> response) {
                houses=response.body().getHouses();
                Update(houses);
            }

            @Override
            public void onFailure(Call<HouseList> call, Throwable t) {

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

       return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
//            case R.id.action_tools :
//
//                return true;


        }
        return super.onOptionsItemSelected(item);
    }
// снизу сделать по красоте но пока что пущай так будет
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        //комнаты
        boolean h1=prefs.getBoolean("h1",false);
        boolean h2=prefs.getBoolean("h2",false);
        boolean h3=prefs.getBoolean("h3",false);
        boolean h4=prefs.getBoolean("h4",false);
        boolean h5=prefs.getBoolean("h5",false);
        //тип недвижки
        boolean ofice=prefs.getBoolean("ofiss",false);
        boolean kvart=prefs.getBoolean("kvart",false);
        boolean houss=prefs.getBoolean("house",false);
        boolean land=prefs.getBoolean("ground",false);

        if(h1!=false||h2!=false||h3!=false||h4!=false||h5!=false||ofice!=false||kvart!=false||houss!=false||land!=false) {
            ArrayList<House> temp = new ArrayList<House>();
            if (h1) {
                for (House h : houses) {
                    if (h.getRooms() != null && h.getRooms().equals("1")) temp.add(h);
                }
                // Update(temp);
            }
            if (h2) {
                for (House h : houses) {
                    if (h.getRooms() != null && h.getRooms().equals("2")) temp.add(h);
                }
            }
            if (h3) {
                for (House h : houses) {
                    if (h.getRooms() != null && h.getRooms().equals("3")) temp.add(h);
                }
            }
            if (h4) {
                for (House h : houses) {
                    if (h.getRooms() != null && h.getRooms().equals("4")) temp.add(h);
                }
            }
            if (h5) {
                for (House h : houses) {
                    if (h.getRooms() != null && h.getRooms().equals("5")) temp.add(h);
                }
            }
            if (ofice) {
                for (House h : houses) {
                    if (h.getType() != null && h.getType().equals("офисные помещения")||h.getType().equals("офис")) temp.add(h);
                }
            }
            if (kvart) {
                for (House h : houses) {
                    if (h.getType() != null && h.getType().equals("квартира")) temp.add(h);
                }
            }
            if (houss) {
                for (House h : houses) {
                    if (h.getType() != null && h.getType().equals("дом в городской черте")||h.getType().equals("таунхаус")||h.getType().equals("дом")) temp.add(h);
                }
            }
            if (land) {
                for (House h : houses) {
                    if (h.getType() != null && h.getType().equals("участок в городской черте")||h.getType().equals("участок")) temp.add(h);
                }
            }
            Update(temp);
        }
        else
        {
            Update(houses);
        }
    }

    private void Update(ArrayList<House> h)
    {
        adapter=null;
        adapter=new HouseAdapter(h,getApplicationContext());
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        adapter.getFilter().filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
        return false;
    }

    @Override
    public void onHouseSelected(House contact) {

    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
