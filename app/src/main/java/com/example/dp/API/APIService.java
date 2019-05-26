package com.example.dp.API;

import com.example.dp.Model.Agent;
import com.example.dp.Model.AgentsList;
import com.example.dp.Model.Home;
import com.example.dp.Model.HouseList;
import com.example.dp.Model.PictureList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService
{
    @GET("estate/search?key=6d35e1f591aa413189aa34cd93dc26fb&status=0&count=850")
    Call<HouseList> getUsers();
    @GET("estate/search?key=6d35e1f591aa413189aa34cd93dc26fb&status=0&count=200")
    Call<HouseList> getUserss();
    @GET("estate/info?key=6d35e1f591aa413189aa34cd93dc26fb")
    Call<Home> getData(@Query("id") int id);
    @GET("agent/info?key=6d35e1f591aa413189aa34cd93dc26fb")
    Call<Agent> getAgent(@Query("id") int id);
    @GET("agent/all?key=6d35e1f591aa413189aa34cd93dc26fb")
    Call<AgentsList> getAgents();
    @GET("picture/EstatePhoto?key=6d35e1f591aa413189aa34cd93dc26fb&estate_id={id}&width=640&height=480&crop=1&watermark=0")
    Call<PictureList> getPictures(@Path("id") int id);
}


