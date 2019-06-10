package com.example.dp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.dp.API.HouseDao;
import com.example.dp.App.App;
import com.example.dp.Model.AppDatabase;
import com.example.dp.Model.House;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class HouseDaoTest {

    private AppDatabase db;
    private HouseDao houseDao;

    @Before
    public void createDb() throws Exception {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .build();
        houseDao = db.houseDao();

    }

    @After
    public void closeDb() throws Exception {
        db.close();
    }

    @Test
    public void whenInsertHouseThenReadTheSameOne() throws Exception {
        List<House> houses = HouseTestHelper.createListOfHouse();

        houseDao.insert(houses.get(0));
        List<House> dbEmployees = houseDao.getAll();

        assertEquals(1, dbEmployees.size());
        assertTrue(HouseTestHelper.housesAreIdentical(houses.get(0), dbEmployees.get(0)));
    }
}
