package com.example.dp.View;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dp.API.HouseDao;
import com.example.dp.App.App;
import com.example.dp.Controller.FavoriteAdapter;
import com.example.dp.Controller.HouseAdapter;
import com.example.dp.Model.AppDatabase;
import com.example.dp.Model.House;
import com.example.dp.R;
import com.example.dp.SuperActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private AppDatabase db;
    private RecyclerView rv;
    private FavoriteAdapter adapter;
    private ProgressBar progressBar;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = App.getInstance().getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        rv=(RecyclerView)v.findViewById(R.id.rv);
        progressBar=v.findViewById(R.id.loading_spinner);
        progressBar.setVisibility(ProgressBar.GONE);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        getHouses();
        return v;
    }
    private void getHouses() {
        class GetTasks extends AsyncTask<Void, Void, List<House>> {

            @Override
            protected List<House> doInBackground(Void... voids) {
                HouseDao houseDao = db.houseDao();
                List<House> houses=new ArrayList<>();
                houses=houseDao.getAll();
                return houses;
            }

            @Override
            protected void onPostExecute(List<House> tasks) {
                super.onPostExecute(tasks);
                adapter = new FavoriteAdapter(tasks,getActivity());
                rv.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        getHouses();
    }

}
