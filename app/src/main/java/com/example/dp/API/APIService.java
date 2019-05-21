package com.example.dp.API;

import com.example.dp.Model.Agent;
import com.example.dp.Model.Home;
import com.example.dp.Model.HouseList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService
{
    @GET("estate/search?key=6d35e1f591aa413189aa34cd93dc26fb")
    Call<HouseList> getUsers();
    @GET("estate/info?key=6d35e1f591aa413189aa34cd93dc26fb")
    Call<Home> getData(@Query("id") int id);
    @GET("agent/info?key=6d35e1f591aa413189aa34cd93dc26fb")
    Call<Agent> getAgent(@Query("id") int id);
    @GET("agent/all?key=6d35e1f591aa413189aa34cd93dc26fb")
    Call<Agent> getAgents();
}


