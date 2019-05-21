package com.example.dp.View;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dp.API.APIService;
import com.example.dp.API.APIUrl;
import com.example.dp.API.HouseDao;
import com.example.dp.App.App;
import com.example.dp.MapsActivity;
import com.example.dp.Model.Agent;
import com.example.dp.Model.AppDatabase;
import com.example.dp.Model.Home;
import com.example.dp.Model.House;
import com.example.dp.Model.HouseLab;
import com.example.dp.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HouseFragment extends Fragment {
    private AppDatabase db;
    private static final String ARG_HOUSE_ID = "house_id";
    private House house;
    private TextView TitleField;
    private ImageView image;
    private TextView Etazz;
    private TextView Mkv;
    private TextView Rooms;
    private TextView Price;
    private TextView Discr;
    private TextView Typen;
    private TextView Sdel;
    private TextView Etaza;
    private TextView Raion;
    private ImageButton mapBtn;
    private ImageButton phnBtn;
    private String phone;
    private int Id;
    private TextView Lng;
    private TextView Lat;
    private Button FawBtn;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    public HouseFragment() {
        // Required empty public constructor
    }

    public static HouseFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ARG_HOUSE_ID, id);
        HouseFragment fragment = new HouseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Id = (int)getArguments().getSerializable(ARG_HOUSE_ID);
        house= HouseLab.get(getActivity()).getHouse(Id);
        db = App.getInstance().getDatabase();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_houses, container, false);
        ///////nazvanie
        TitleField = (TextView) v.findViewById(R.id.textHouse);
        TitleField.setText(house.getTitle());
        Price = (TextView) v.findViewById(R.id.price);
        Price.setText("Стоимость: " + currencyFormat.format(Integer.parseInt(house.getPrice_total())));
        ////////block metri i td
        Etazz = (TextView) v.findViewById(R.id.etaz);
        Etazz.setText(house.getFloor());
        Mkv = (TextView) v.findViewById(R.id.mkv);
        Mkv.setText(house.getArea());
        Rooms = (TextView) v.findViewById(R.id.rooms);
        Rooms.setText(house.getRooms());
        //////////opisanie
        Discr = (TextView) v.findViewById(R.id.opis);
        Discr.setText(house.getDescription());
        phnBtn=(ImageButton) v.findViewById(R.id.imageButton2);

        image=v.findViewById(R.id.pictureHouse);
        Picasso.get().load(house.getPicture_path()).into(image);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        Call<Home> call=service.getData(Id);
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                int h= response.body().getProperty().getAgentId();
                Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
                APIService service=retrofit.create(APIService.class);
                Call<Agent> call1=service.getAgent(h);
                call1.enqueue(new Callback<Agent>() {
                    @Override
                    public void onResponse(Call<Agent> call, Response<Agent> response) {
                        phone=response.body().getAgent().getMobilePhone().replace(" ","").replace("-","").replace("(","").replace(")","");

                    }

                    @Override
                    public void onFailure(Call<Agent> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {

            }
        });

        ////////////harakteristiki

        Typen = (TextView) v.findViewById(R.id.typen);
        Typen.setText(house.getType());
        Sdel = (TextView) v.findViewById(R.id.sdel);
        Sdel.setText(house.getDeal());
        Etaza = (TextView) v.findViewById(R.id.etaza);
        Etaza.setText(house.getFloor());
        Raion = (TextView) v.findViewById(R.id.raion);
        Raion.setText(house.getDistrict_title());

        mapBtn= (ImageButton) v.findViewById(R.id.mapbtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MapsActivity.class);

                intent.putExtra("Lat", Lat.getText().toString());
                intent.putExtra("Lng", Lng.getText().toString());
                intent.putExtra("title",TitleField.getText().toString());
                startActivity(intent);
            }
        });

        phnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+phone));
                startActivity(callIntent);
            }
        });


        FawBtn=v.findViewById(R.id.fawBtn);
        FawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class SaveHouse extends AsyncTask<Void,Void,Void>
                {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        HouseDao houseDao = db.houseDao();
                        houseDao.insert(house);
                        return null;
                    }
                }
                SaveHouse st = new SaveHouse();
                st.execute();
                getActivity().finish();
            }
        });


        Lat = (TextView) v.findViewById(R.id.Lat);
        Lat.setText(house.getLatitude());
        Lng = (TextView) v.findViewById(R.id.Lng);
        Lng.setText(house.getLongitude());




        return v;
    }
}
