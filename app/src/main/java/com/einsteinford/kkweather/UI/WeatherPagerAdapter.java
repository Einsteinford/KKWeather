package com.einsteinford.kkweather.UI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.einsteinford.kkweather.Activity.MainActivity;
import com.einsteinford.kkweather.Activity.WeatherFragment;


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
            return new WeatherFragment();
    }
}
