package com.example.dp.View;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

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
public class FavFragment extends Fragment {
    private AppDatabase db;
    private static final String ARG_HOUSE_ID = "house_id";
    private House house;
    private TextView TitleField;
    private ImageView image;
    private TextView Etazz;
    private TextView Mkv;
    private TextView Mkvz;
    private TextView Mkvk;
    private TextView Rooms;
    private TextView Price;
    private TextView Discr;
    private TextView Typen;
    private TextView Sdel;
    private TextView Etaza;
    private String agentName;
    private TextView Raion;
    private ImageButton mapBtn;
    private Button phnBtn;
    private String phone;
    private int Id;
    private TextView Lng;
    private TextView Lat;
    private Button update;
    private Button delete;
    private EditText comment;
    private Handler mHandler;
    private RecyclerView hrv;
    private PictureAdapter pictureAdapter;
    private FloatingActionButton fabbb;
    Calendar dateAndTime=Calendar.getInstance();
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    public FavFragment() {
        // Required empty public constructor
    }

    public static FavFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ARG_HOUSE_ID, id);
        FavFragment fragment = new FavFragment();
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
        View v= inflater.inflate(R.layout.fragment_fav, container, false);
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
        phnBtn=(Button) v.findViewById(R.id.button2);

        image=v.findViewById(R.id.pictureHouse);
        comment=v.findViewById(R.id.comment);
        comment.setText(house.getComment());
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

        fabbb=(FloatingActionButton) v.findViewById(R.id.fabbbb);
        fabbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE), true)
                        .show();

                new DatePickerDialog(getActivity(), d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });



        mapBtn= (ImageButton) v.findViewById(R.id.mapbtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MapsActivity.class);

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


        update=v.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                house.setComment(comment.getText().toString());
                class UpdateHouse extends AsyncTask<Void,Void,Void>
                {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        HouseDao houseDao = db.houseDao();
                        houseDao.update(house);
                        return null;
                    }
                }
                UpdateHouse st = new UpdateHouse();
                st.execute();
                getActivity().finish();
            }
        });
        delete=v.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class DeleteHouse extends AsyncTask<Void,Void,Void>
                {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        HouseDao houseDao = db.houseDao();
                        houseDao.delete(house);
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        getActivity().finish();
                    }
                }
                DeleteHouse st = new DeleteHouse();
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
        String uri = APIUrl.BASE_URL + "picture/EstatePhoto?key=6d35e1f591aa413189aa34cd93dc26fb&estate_id="+Id+"&width=1280&height=1024&crop=1&watermark=0";
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
                            PictureList pl=gson.fromJson(ob.toString(),PictureList.class);
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




    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            //setInitialDateTime();
        }
    };



    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
           // setInitialDateTime();
        }
    };
}
