package com.einsteinford.kkweather.Fragment;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.einsteinford.kkweather.R;
import com.einsteinford.kkweather.Utils.HttpUtil;
import com.einsteinford.kkweather.Utils.JsonUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by KK on 2016-08-23.
 */
public class WeatherFragment extends android.support.v4.app.Fragment {
    String TAG = getClass().getSimpleName();
    private TextView mTvWeather;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.weather_fragment, container, false);
        String ID = getArguments().getString("ID");
        mTvWeather = (TextView) view.findViewById(R.id.tv_weather);
//        view.setBackgroundResource(R.color.main_page_background);
        mTvWeather.setText(ID);

        return view;
    }

}
