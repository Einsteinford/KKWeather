package com.einsteinford.kkweather.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.einsteinford.kkweather.Activity.MainActivity;
import com.einsteinford.kkweather.Fragment.WeatherFragment;


/**
 * Created by KK on 2016-08-23.
 */
public class WeatherPagerAdapter extends FragmentPagerAdapter {

    String TAG = getClass().getSimpleName();

    public WeatherPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.i(TAG, "WeatherPagerAdapter: ");
    }

    @Override
    public int getCount() {
        return MainActivity.PAGE_NUM;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i(TAG, "getItem: " + position);
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);

        return fragment;
    }
}
