package com.einsteinford.kkweather.app;

import android.app.Application;

import com.einsteinford.kkweather.db.DaoMaster;
import com.einsteinford.kkweather.db.DaoSession;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.database.Database;

/**
 * Created by KK on 17-2-15.
 */

public class KKWeatherApplication extends Application {
    private static final String TAG = "kkTest";
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init(TAG);
        setupDataBase();
    }

    private void setupDataBase() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "weather-db");
        Database database = devOpenHelper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }
}
