package com.example.dp;

import com.example.dp.Model.House;

import java.util.ArrayList;
import java.util.List;

public class HouseTestHelper {


    public static List<House> createListOfHouse() {
        House house=new House("434","eee","tt"
                ,"tt","30","ggg","rrertr"
                ,false,"rr","regre","ergrfd"
                ,true,10000000,"tt","tt","30"
                ,"ggg","rrertr","rrr","rr"
                ,"regre","ergrfd","rrr"
                ,"rrr","ggg","rrertr","rrr");
        List<House> list=new ArrayList<>();
        list.add(house);
        return list;
    }

    public static boolean housesAreIdentical(House house, House house1) {
        return house.equals(house1);
    }
}
