package com.example.dp;

import android.content.Context;
import android.content.Intent;
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

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        if (googleMap != null) {

            // More info: https://developers.google.com/maps/documentation/android/infowindows
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

       // mMap.setMinZoomPreference(10);
        LatLng ny = new LatLng(54.7113506, 20.5061358);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ny,10));

    }
}
