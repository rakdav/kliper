package com.example.dp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dp.Model.House;
import com.example.dp.Model.HouseLab;
import com.example.dp.View.FavFragment;
import com.example.dp.View.HouseFragment;

import java.util.List;

public class FavoritePagerActivity extends AppCompatActivity {

    private static final String EXTRA_HOUSE_ID =
            "com.example.dp.id";
    private static final String EXTRA_YOUSES =
            "com.example.dp.houses";
    private ViewPager mViewpager;
    private List<House> houses;

    public static Intent newIntent(Context packageContext, int Id) {
        Intent intent = new Intent(packageContext, FavoritePagerActivity.class);
        intent.putExtra(EXTRA_HOUSE_ID, Id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
      //  mViewpager = (ViewPager) findViewById(R.id.act_view);
        int Id = getIntent().getIntExtra(EXTRA_HOUSE_ID, 0);
        houses = HouseLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        houses=HouseLab.get(this).getCrimes();
        int number=-1;
        for (int i = 0; i < houses.size(); i++) {
            if (houses.get(i).getId()==Id) {
                number=i;
                break;
            }
        }
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.act_view,FavFragment.newInstance(houses.get(number).getId()));
        fragmentTransaction.commit();
//        mViewpager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
//            @Override
//            public Fragment getItem(int position) {
//                House house = houses.get(position);
//                return FavFragment.newInstance(house.getId());
//            }
//
//            @Override
//            public int getCount() {
//                return houses.size();
//            }
//        });
//        for (int i = 0; i < houses.size(); i++) {
//            if (houses.get(i).getId() == Id) {
//                mViewpager.setCurrentItem(i);
//                break;
//            }
//        }
    }
}