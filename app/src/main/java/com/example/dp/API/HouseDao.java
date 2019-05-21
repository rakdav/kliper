package com.example.dp.API;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dp.Model.House;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface HouseDao
{
    @Query("SELECT * FROM house")
    List<House> getAll();

    @Query("SELECT * FROM house WHERE id = :id")
    House getById(long id);

    @Insert
    void insert(House employee);

    @Update
    void update(House employee);

    @Delete
    void delete(House employee);
}
