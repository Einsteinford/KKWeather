package com.einsteinford.kkweather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.einsteinford.kkweather.app.KKWeatherApplication;
import com.einsteinford.kkweather.db.DaoSession;
import com.einsteinford.kkweather.db.DbBasicBeanDao;
import com.einsteinford.kkweather.db.DbDailyForecastBeanDao;
import com.einsteinford.kkweather.utils.ActivityCollector;

public class BaseActivity extends AppCompatActivity {
    private DbBasicBeanDao mBasicBeanDao;
    private DbDailyForecastBeanDao mDbDailyForecastBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        initDb();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private void initDb() {     //取得数据库操作
        DaoSession daoSession = ((KKWeatherApplication) getApplication()).getDaoSession();
        mBasicBeanDao = daoSession.getDbBasicBeanDao();
        mDbDailyForecastBeanDao = daoSession.getDbDailyForecastBeanDao();
    }

    public DbBasicBeanDao getBasicBeanDao() {
        return mBasicBeanDao;
    }

    public DbDailyForecastBeanDao getDailyForecastBeanDao() {
        return mDbDailyForecastBeanDao;
    }
}
