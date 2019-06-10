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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dp.API.APIService;
import com.example.dp.API.APIUrl;
import com.example.dp.Controller.HouseAdapter;
import com.example.dp.Model.House;
import com.example.dp.Model.HouseList;
import com.example.dp.R;
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
    private RecyclerView rv;
    private HouseAdapter adapter;
    private ArrayList<House> houses;
    private ProgressBar progressBar;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        rv=v.findViewById(R.id.rv);
        progressBar=v.findViewById(R.id.loading_spinner);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        houses=new ArrayList<>();
        progressBar.setVisibility(ProgressBar.VISIBLE);
        Call<HouseList> call=service.getUsers();
        call.enqueue(new Callback<HouseList>() {
            @Override
            public void onResponse(Call<HouseList> call, Response<HouseList> response) {
                houses=response.body().getHouses();
                progressBar.setVisibility(ProgressBar.GONE);
                Update(houses);
            }

            @Override
            public void onFailure(Call<HouseList> call, Throwable t) {
                progressBar.setVisibility(ProgressBar.GONE);
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
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
        //тип сделки
        boolean arenda=prefs.getBoolean("q2",false);
        boolean prodaza=prefs.getBoolean("q1",false);
        //город
        String town = prefs.getString("city",null);
        //boolean townn = prefs.getBoolean("city",false);

           if(h1==false&&h2==false&&h3==false&&h4==false&&h5==false&&ofice==false&&kvart==false&&houss==false&&land==false&&arenda==false&&prodaza==false&&town==null)
           {
               Update(houses);
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
                                       if ( h.getCity_title()!= null && h.getCity_title().equals("Светлогорск")) t3.add(h);
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
                               Update(t3);
                           }
                       }
                   }
               }
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