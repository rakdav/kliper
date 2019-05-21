package com.example.dp.Model;

import java.util.ArrayList;

public class HouseList
{
    private ArrayList<House> results;

    public HouseList() {
    }

    public ArrayList<House> getHouses() {
        return results;
    }

    public void setHouses(ArrayList<House> houses) {
        this.results = houses;
    }
}
