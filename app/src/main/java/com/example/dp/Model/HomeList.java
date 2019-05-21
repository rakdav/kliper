package com.example.dp.Model;

import java.util.ArrayList;

public class HomeList {
    private ArrayList<Home> property;

    public HomeList() {
    }

    public ArrayList<Home> getHome() {
        return property;
    }

    public void setHome(ArrayList<Home> homes) {
        this.property = homes;
    }
}
