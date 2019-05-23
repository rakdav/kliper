package com.example.dp.View;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dp.API.APIService;
import com.example.dp.API.APIUrl;
import com.example.dp.Controller.HouseAdapter;
import com.example.dp.Model.House;
import com.example.dp.Model.HouseList;
import com.example.dp.R;
import com.example.dp.SettingsActivity;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private HouseAdapter adapter;
    private RecyclerView rv;
    private ArrayList<House> houses;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        rv=(RecyclerView)v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());
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

        if(ofice!=false||kvart!=false||houss!=false||land!=false) {
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


            if(temp.size() !=0 && h1!=false||h2!=false||h3!=false||h4!=false||h5!=false) {
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

                if (t1.size() !=0){

                }


                    Update(t1);
                }
                else {
                    Update(temp);
                }
        }
        else
        {
            Update(houses);
        }
    }






    private void Update(ArrayList<House> h)
    {
        adapter=null;
        adapter=new HouseAdapter(h,getActivity());
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }

}
