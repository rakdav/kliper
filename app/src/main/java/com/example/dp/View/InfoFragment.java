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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoFragment extends Fragment {
    private AgentsAdapter adapter;
    private RecyclerView rv;
    private ArrayList<Agent> agents;

    public InfoFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.info_glav, container, false);
        rv=v.findViewById(R.id.rvagents);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        Retrofit retrofit=new Retrofit.Builder().baseUrl(APIUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIService service=retrofit.create(APIService.class);
        agents=new ArrayList<>();
        Call<AgentsList> call=service.getAgents();
        call.enqueue(new Callback<AgentsList>() {
            @Override
            public void onResponse(Call<AgentsList> call, Response<AgentsList> response) {
                agents=response.body().getResults();
                Update(agents);
            }

            @Override
            public void onFailure(Call<AgentsList> call, Throwable t) {

            }
        });




        return v;

    }


    private void Update(ArrayList<Agent> h)
    {
        adapter=null;
        adapter=new AgentsAdapter(h,getActivity());
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }

}
