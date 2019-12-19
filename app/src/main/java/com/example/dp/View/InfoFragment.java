package com.example.dp.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dp.API.APIService;
import com.example.dp.API.APIUrl;
import com.example.dp.Controller.AgentsAdapter;
import com.example.dp.Controller.HouseAdapter;
import com.example.dp.Model.Agent;
import com.example.dp.Model.AgentsList;
import com.example.dp.Model.House;
import com.example.dp.Model.HouseList;
import com.example.dp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoFragment extends Fragment implements OnMapReadyCallback {
    private AgentsAdapter adapter;
    private RecyclerView rv;
    private ArrayList<Agent> agents;

    private MapView mapView;
    private GoogleMap gmap;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    public InfoFragment() {
       //Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.info_glav, container, false);
//        rv=v.findViewById(R.id.rvagents);
//        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        APIService service=retrofit.create(APIService.class);
//        agents=new ArrayList<>();
//        Call<AgentsList> call=service.getAgents();
//        call.enqueue(new Callback<AgentsList>() {
//            @Override
//            public void onResponse(Call<AgentsList> call, Response<AgentsList> response) {
//                agents=response.body().getResults();
//                Update(agents);
//            }
//
//            @Override
//            public void onFailure(Call<AgentsList> call, Throwable t) {
//
//            }
//        });

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = v.findViewById(R.id.mapView2);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
        return v;
    }

    private void Update(ArrayList<Agent> h)
    {
        adapter=null;
        adapter=new AgentsAdapter(h,getActivity());
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng ny = new LatLng(54.7113506, 20.5061358);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ny).title("Недвижимость Калининграда АН Клипер");
        gmap.addMarker(markerOptions);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }
}
