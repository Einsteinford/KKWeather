package com.einsteinford.kkweather.app;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by KK on 17-2-15.
 */

public class KKWeatherApplication extends Application {
    private static final String TAG = "kkTest";
//    private static KKWeatherApplication kkWeatherApplication;
//
//    public static KKWeatherApplication getInstance() {
//        return kkWeatherApplication;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//        kkWeatherApplication = this;
        Logger.init(TAG);
    }
}
