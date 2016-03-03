package com.steinsvik.fabricmanagaer.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.steinsvik.fabricmanagaer.fragment.DateFragment;
import com.steinsvik.fabricmanagaer.fragment.MonthFragment;
import com.steinsvik.fabricmanagaer.fragment.YearFragment;

/**
 * Created by Gigabyte on 3/1/2016.
 */
public class TabAdapter extends FragmentStatePagerAdapter {
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DateFragment();
            case 1:
                return new MonthFragment();
            case 2:
                return new YearFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
