package com.einsteinford.kkweather.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.einsteinford.kkweather.Utils.ActivityCollector;

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
