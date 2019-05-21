package com.example.dp.Model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.dp.API.HouseDao;

@Database(entities = {House.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract HouseDao houseDao();
}
