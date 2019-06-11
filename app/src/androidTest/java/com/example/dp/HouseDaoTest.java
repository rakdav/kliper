package com.example.dp;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.runner.AndroidJUnit4;

import com.example.dp.API.HouseDao;
import com.example.dp.App.App;
import com.example.dp.Model.Agent;
import com.example.dp.Model.AppDatabase;
import com.example.dp.Model.House;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
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


    @Test
    public void listItemClick() throws Exception {
        Agent item = new Agent();
        item.setId(1);

        onData(equalTo(item)).perform(click());

        onView(withId(R.id.group_name)).check(matches(withText("1")));
    }



    @Test
    public void listItemClick2() throws Exception {
        onData(withItemName(is("Item 1"))).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("Item 1")));
    }


    static Matcher<Object> withItemName(final Matcher<String> itemNameMatcher) {
        return new BoundedMatcher<Object,Agent>(Agent.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with item name: ");
                itemNameMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(Agent agent) {
                return itemNameMatcher.matches(Agent.class.getName());
            }
        };
    }





    @RunWith(AndroidJUnit4.class)
    public class FirstActivityTest {

        @Rule
        public ActivityTestRule<SuperActivity> activityTestRule = new ActivityTestRule<SuperActivity>(SuperActivity.class);

        @Test
        public void startSecondActivityAndReturnResultCancel() throws Exception {
            // first activity, button click
            onView(withId(R.id.fabSearch)).perform(click());

            // second activity, text check
            onView(withId(R.id.text)).check(matches(withText("1, Name 1")));

            // second activity, press back
            pressBack();

            // first activity, text check
            onView(withId(R.id.text)).check(matches(withText("Cancel")));
        }
    }



}
