package com.einsteinford.kkweather.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.einsteinford.kkweather.fragment.SelectedCitiesFragment;
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

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("city_names",intent.getSerializableExtra("city_names"));

        mFragmentManager = getSupportFragmentManager();
        SelectedCitiesFragment selectedCitiesFragment = new SelectedCitiesFragment();
        selectedCitiesFragment.setArguments(bundle);
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
