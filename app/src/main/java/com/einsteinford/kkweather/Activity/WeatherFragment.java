package com.einsteinford.kkweather.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.einsteinford.kkweather.R;

/**
 * Created by KK on 2016-08-23.
 */
public class WeatherFragment extends android.support.v4.app.Fragment {
    String TAG = getClass().getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.weather_fragment, container,false);
//        view.setBackgroundResource(R.color.main_page_background);

        return view;
    }

    @Override
    public void onDestroy() {
        Log.i("WeatherFragment", "onDestroy: ");
        super.onDestroy();
    }
}
