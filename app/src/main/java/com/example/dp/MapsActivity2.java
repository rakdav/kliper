package com.example.dp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.dp.API.APIService;
import com.example.dp.API.APIUrl;
import com.example.dp.Controller.HouseAdapter;
import com.example.dp.Model.House;
import com.example.dp.Model.HouseList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<House> houses;
    private int id;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        houses = (ArrayList<House>) getIntent().getSerializableExtra("houss");



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
        //тип сделки
        boolean arenda=prefs.getBoolean("q2",false);
        boolean prodaza=prefs.getBoolean("q1",false);
        //город
        String town = prefs.getString("city",null);
        //boolean townn = prefs.getBoolean("city",false);

        if(h1==false&&h2==false&&h3==false&&h4==false&&h5==false&&ofice==false&&kvart==false&&houss==false&&land==false&&arenda==false&&prodaza==false&&town==null)
        {
            return;
        }
        else
        {
            //тип недвижимости
            if(houses.size() !=0) {
                ArrayList<House> temp = new ArrayList<House>();
                if (kvart)
                {
                    for (House h : houses)
                    {
                        if (h.getType() != null && h.getType().equals("квартира")) temp.add(h);
                    }
                }
                if (ofice)
                {
                    for (House h : houses) {
                        if (h.getType() != null && h.getType().equals("офисные помещения")||h.getType().equals("офис")) temp.add(h);
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
                if(ofice==false&&kvart==false&&houss==false&&land==false)
                {
                    temp=houses;
                }
///////////////колво комнат

                if(temp.size() !=0) {
                    ArrayList<House> t1 = new ArrayList<>();
                    if (h1) {
                        for (House h : temp) {
                            if (h.getRooms() != null && h.getRooms().equals("1")) t1.add(h);
                        }
                    }
                    if (h2) {
                        for (House h : temp) {
                            if (h.getRooms() != null && h.getRooms().equals("2")) t1.add(h);
                        }
                    }
                    if (h3) {
                        for (House h : temp) {
                            if (h.getRooms() != null && h.getRooms().equals("3")) t1.add(h);
                        }
                    }
                    if (h4) {
                        for (House h : temp) {
                            if (h.getRooms() != null && h.getRooms().equals("4")) t1.add(h);
                        }
                    }
                    if (h5) {
                        for (House h : temp) {
                            if (h.getRooms() != null && h.getRooms().equals("5")) t1.add(h);
                        }
                    }
                    if(h1==false&&h2==false&&h3==false&&h4==false&&h5==false)
                    {
                        t1=temp;
                    }
                    //////////////тип сделки

                    if (t1.size() !=0){
                        ArrayList<House> t2 = new ArrayList<>();
                        if (arenda) {
                            for (House h : t1) {
                                if ( h.getDeal()!= null && h.getDeal().equals("аренда")) t2.add(h);
                            }
                        }
                        if (prodaza) {
                            for (House h : t1) {
                                if (h.getDeal() != null && h.getDeal().equals("продажа")) t2.add(h);
                            }
                        }
                        if(arenda==false&&prodaza==false)
                        {
                            t2=t1;
                        }
                        //выбор города
                        if(t2.size()!=0){
                            ArrayList<House> t3 = new ArrayList<>();
                            if (town.contains("Калининград")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Калининград")) t3.add(h);
                                }
                            }
                            if (town.contains("Светлогорск")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Cветлогорск")) t3.add(h);
                                }

                            }
                            if (town.contains("Советск")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Советск")) t3.add(h);
                                }
                            }
                            if (town.contains("Зеленоградск")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Зеленоградск")) t3.add(h);
                                }
                            }
                            if (town.contains("Черняховск")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Черняховск")) t3.add(h);
                                }
                            }
                            if (town.contains("Балтийск")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Балтийск")) t3.add(h);
                                }
                            }
                            if (town.contains("Гусев")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Гусев")) t3.add(h);
                                }
                            }
                            if (town.contains("Светлый")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Светлый")) t3.add(h);
                                }
                            }
                            if (town.contains("Гурьевск")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Гурьевск")) t3.add(h);
                                }
                            }
                            if (town.contains("Гвардейск")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Гвардейск")) t3.add(h);
                                }
                            }
                            if (town.contains("Пионерский")){
                                for (House h : t2) {
                                    if ( h.getCity_title()!= null && h.getCity_title().equals("Пионерский")) t3.add(h);
                                }
                            }
                            if(town.contains("false"))
                            {
                                t3=t2;
                            }
                            houses=t3;
                        }
                    }
                }
            }
        }




    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);


        int i=0;

        while (i<houses.size())
        {
            final House house = houses.get(i);
            double Latt= Double.valueOf(house.getLatitude());
            double Lngg= Double.valueOf(house.getLongitude());
            LatLng posit = new LatLng(Latt,Lngg);
            mMap.addMarker(new MarkerOptions().position(posit).title(house.getTitle()));
            i++;
        }


        if (googleMap != null) {

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    String title = marker.getTitle();
                    int j=0;
                    while (j<houses.size())
                    {
                        final House house = houses.get(j);
                        if (house.getTitle().contains(title)){
                            Intent intent = ViewPagerActivity.newIntent(getApplicationContext(),house.getId());
                            getApplicationContext().startActivity(intent);
                            break;
                        }
                        j++;
                    }

                }
            });
        }

        LatLng ny = new LatLng(54.7113506, 20.5061358);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ny,10));

    }
}
