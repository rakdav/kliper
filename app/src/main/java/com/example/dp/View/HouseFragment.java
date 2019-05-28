package com.example.dp.View;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.dp.Controller.PictureAdapter;
import com.example.dp.MapsActivity;
import com.example.dp.Model.Agent;
import com.example.dp.Model.AppDatabase;
import com.example.dp.Model.Home;
import com.example.dp.Model.House;
import com.example.dp.Model.HouseLab;
import com.example.dp.Model.PictureList;
import com.example.dp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HouseFragment extends Fragment {
    public static View.OnClickListener onSet;
    private AppDatabase db;
    private static final String ARG_HOUSE_ID = "house_id";
    private House house;
    private TextView TitleField;
    private ImageView image;
    private TextView Mkv;
    private TextView Mkvz;
    private TextView Mkvk;
    private TextView Rooms;
    private TextView Price;
    private TextView Discr;
    private TextView Typen;
    private TextView Sdel;
    private TextView Etaza;
    private TextView Raion;
    private ImageButton mapBtn;
    private Button phnBtn;
    private String phone;
    private String agentName;
    private int Id;
    private TextView Lng;
    private TextView Lat;
    private Button FawBtn;
    private RecyclerView hrv;
    private PictureAdapter pictureAdapter;
    private Handler mHandler;
    private PictureList pl;

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
       final View v= inflater.inflate(R.layout.fragment_houses, container, false);
        onSet=new onSet(getContext());
        ///////nazvanie
        mHandler = new Handler(Looper.getMainLooper());
        TitleField = (TextView) v.findViewById(R.id.textHouse);
        TitleField.setText(house.getTitle());
        Price = (TextView) v.findViewById(R.id.price);
        Price.setText("Стоимость: " + currencyFormat.format(Integer.parseInt(house.getPrice_total())));
        ////////block metri i td
        Mkv = (TextView) v.findViewById(R.id.mkv);
        Mkv.setText(house.getArea()+" кв.м");
        Mkvz = (TextView) v.findViewById(R.id.mkvz);
        Mkvz.setText(house.getLive_area()+" кв.м");
        Mkvk = (TextView) v.findViewById(R.id.mkvk);
        Mkvk.setText(house.getKitchen_area()+" кв.м");
        Rooms = (TextView) v.findViewById(R.id.rooms);
        Rooms.setText(house.getRooms());
        //////////opisanie
        Discr = (TextView) v.findViewById(R.id.opis);
        Discr.setText(house.getDescription().replaceAll("(?u)[^а-яА-я.,0-9 ]", ""));

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
                        agentName=response.body().getAgent().getName();
                        phnBtn.setText(agentName);
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

        phnBtn=(Button) v.findViewById(R.id.button2);

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
        hrv=v.findViewById(R.id.hrv);
        hrv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        String uri = APIUrl.BASE_URL + "picture/EstatePhoto?key=6d35e1f591aa413189aa34cd93dc26fb&estate_id="+Id+"&width=1280&height=1024&crop=0&watermark=0";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(uri)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
            }

            @Override
            public void onResponse(okhttp3.Call call, final okhttp3.Response response) throws IOException {

                final String res = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject ob = new JSONObject(res);
                            Gson gson=new Gson();
                            pl=gson.fromJson(ob.toString(),PictureList.class);
                            pictureAdapter=new PictureAdapter(pl.getResults(),getActivity());
                            hrv.setAdapter(pictureAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                });
            }
        });

        return v;
    }
    public class onSet implements View.OnClickListener
    {
        private final Context context;

        private onSet(Context context) {
            this.context = context;
        }
        @Override
        public void onClick(View v) {
            removeItem(v);
        }
        private void removeItem(View v) {
            int selectedItemPosition = hrv.getChildPosition(v);

            Picasso.get().load(pl.getResults().get(selectedItemPosition).getUrl()).into(image);
        }
    }
}
