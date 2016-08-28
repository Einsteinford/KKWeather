package com.einsteinford.kkweather.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.einsteinford.kkweather.sql.WeatherDbSchema.*;
/**
 * Created by KK on 2016-08-22.
 */
public class WeatherSQLiteHelper extends SQLiteOpenHelper {

    public static final String CreateTableCityID = "create table " + CityIdTable.NAME + " (" +
            CityIdTable.Cols.ID+" text primary key," +
            CityIdTable.Cols.CTCN+" text," +
            CityIdTable.Cols.CTEN+" text," +
            CityIdTable.Cols.REG+" text," +
            CityIdTable.Cols.PROV+" text," +
            CityIdTable.Cols.LAT+" text," +
            CityIdTable.Cols.LON+" text)";
    public static final String CreateTableCityWeather = "create table " + CityWeatherTable.NAME + " (" +
            CityWeatherTable.Cols.ID + " text primary key," +
            CityWeatherTable.Cols.CITY + " text," +
            CityWeatherTable.Cols.NOW_COND_TXT + " text," +
            CityWeatherTable.Cols.NOW_TMP + " text," +
            CityWeatherTable.Cols.FIRST_DATE + " text," +
            CityWeatherTable.Cols.FIRST_TXT_D + " text," +
            CityWeatherTable.Cols.FIRST_TXT_N + " text," +
            CityWeatherTable.Cols.FIRST_TMP_MAX + " text," +
            CityWeatherTable.Cols.FIRST_TMP_MIN + " text," +
            CityWeatherTable.Cols.SECOND_DATE + " text," +
            CityWeatherTable.Cols.SECOND_TXT_D + " text," +
            CityWeatherTable.Cols.SECOND_TXT_N + " text," +
            CityWeatherTable.Cols.SECOND_TMP_MAX + " text," +
            CityWeatherTable.Cols.SECOND_TMP_MIN + " text," +
            CityWeatherTable.Cols.THIRD_DATE + " text," +
            CityWeatherTable.Cols.THIRD_TXT_D + " text," +
            CityWeatherTable.Cols.THIRD_TXT_N + " text," +
            CityWeatherTable.Cols.THIRD_TMP_MAX + " text," +
            CityWeatherTable.Cols.THIRD_TMP_MIN + " text," +
            CityWeatherTable.Cols.FOURTH_DATE + " text," +
            CityWeatherTable.Cols.FOURTH_TXT_D + " text," +
            CityWeatherTable.Cols.FOURTH_TXT_N + " text," +
            CityWeatherTable.Cols.FOURTH_TMP_MAX + " text," +
            CityWeatherTable.Cols.FOURTH_TMP_MIN + " text," +
            CityWeatherTable.Cols.FIFTH_DATE + " text," +
            CityWeatherTable.Cols.FIFTH_TXT_D + " text," +
            CityWeatherTable.Cols.FIFTH_TXT_N + " text," +
            CityWeatherTable.Cols.FIFTH_TMP_MAX + " text," +
            CityWeatherTable.Cols.FIFTH_TMP_MIN + " text)";

    private static WeatherSQLiteHelper mHelper;

    private WeatherSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //构造方法私有化
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getDatabaseInstance(Context context) {
        //构造数据库的单例
        if (mHelper == null) {
            Log.i("WeatherSQLiteHelper", "Create!!");
            mHelper = new WeatherSQLiteHelper(context, WeatherDbSchema.DBNAME, null, 1);
            //基本上，程序开启后，利用StarActivity实例化了它，所以以后的Activity都是调用StarActivity下的数据库操作实例
            //如果随着活动的销毁，数据库操作实例也会销毁的话，那就简单了
        }
        return mHelper.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CreateTableCityID);
            db.execSQL(CreateTableCityWeather);
            //属于IO操作，进行try
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
