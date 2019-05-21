package com.example.dp.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class HouseLab
{
    private static HouseLab houseLab;
    private List<House> houses;
    public static HouseLab get(Context context) {
        if (houseLab == null) {
            houseLab = new HouseLab(context);
        }
        return houseLab;
    }
    private HouseLab(Context context) {
        houses=new ArrayList<House>();
    }
    public List<House> getCrimes() {
        return houses;
    }
    public void AddHouses(List<House> h)
    {
        houses.addAll(h);
    }
    public House getHouse(int id) {
        for (House crime : houses) {
            if (crime.getId()==id) {
                return crime;
            }
        }
        return null;
    }
    public static void Destroy()
    {
        houseLab=null;
    }

}
