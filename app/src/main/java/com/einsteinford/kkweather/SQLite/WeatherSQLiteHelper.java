package com.einsteinford.kkweather.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KK on 2016-08-22.
 */
public class WeatherSQLiteHelper extends SQLiteOpenHelper {
    public static final String CreateTableCityID = "create table city_id (" +
            "id text primary key," +
            "city_chinese text," +
            "city_english text," +
            "region text," +
            "province text," +
            "latitude text," +
            "longitude text)";

    public static final String CreateTableAttractionsID = "create table attractions_id ("
            + "id text primary key,"
            + "city_chinese text,"
            + "latitude text,"
            + "longitude text)";

    private static WeatherSQLiteHelper mHelper;

    private WeatherSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //构造方法私有化
        super(context, name, factory, version);
    }

    public static synchronized SQLiteDatabase getDatabaseInstance(Context context) {
        //构造数据库的单例
        if (mHelper == null) {
            Log.i("WeatherSQLiteHelper", "Create!!");
            mHelper = new WeatherSQLiteHelper(context, "Weather.db", null, 1);
            //基本上，程序开启后，利用StarActivity实例化了它，所以以后的Activity都是调用StarActivity下的数据库操作实例
            //如果随着活动的销毁，数据库操作实例也会销毁的话，那就简单了
        }
        return mHelper.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CreateTableCityID);
//            db.execSQL(CreateTableAttractionsID);
            //暂时不做景区的
            //属于IO操作，进行try
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
