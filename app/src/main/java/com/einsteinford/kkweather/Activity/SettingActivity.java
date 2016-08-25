package com.einsteinford.kkweather.Activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.einsteinford.kkweather.Fragment.CityListViewFragment;
import com.einsteinford.kkweather.Fragment.SelectedCitiesFragment;
import com.einsteinford.kkweather.R;

/**
 * Created by KK on 2016-08-24.
 */
public class SettingActivity extends BaseActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Log.i("SettingActivity", "onCreate: ");

        mFragmentManager = getSupportFragmentManager();
        SelectedCitiesFragment selectedCitiesFragment = new SelectedCitiesFragment();
        replaceFragment(selectedCitiesFragment);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_setting);
//        setSupportActionBar(toolbar);  //将toolbar设置为ActionBar,确保toolbar已经布置在layout中，切不为空
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                    CityListViewFragment cityListViewFragment = new CityListViewFragment();
//                    replaceFragment(cityListViewFragment);
//                    return true;
//            }
//        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //关联菜单项
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("SettingActivity", "onDestroy: ");
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.setting_fragment, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }
}
