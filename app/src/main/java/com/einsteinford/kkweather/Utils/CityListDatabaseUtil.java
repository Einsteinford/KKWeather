package com.einsteinford.kkweather.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.einsteinford.kkweather.SQLite.WeatherSQLiteHelper;

/**
 * Created by KK on 2016-08-16.
 */
public class CityListDatabaseUtil {

    public static void insertCityList(String id, String city_chinese, String city_english, String region,String province, String latitude, String longitude, Context context) {
        //一个可用于添加用户数据的静态方法
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(context);
        //删光表
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("city_chinese", city_chinese);
        contentValues.put("city_english", city_english);
        contentValues.put("region", region);
        contentValues.put("province", province);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        db.insert("city_id", null, contentValues);

    }



    public static void insertAttractionsList(String id, String city_chinese, String latitude, String longitude, Context context) {
        //一个可用于添加用户数据的静态方法
        SQLiteDatabase db = WeatherSQLiteHelper.getDatabaseInstance(context);
        db.execSQL("drop table if exists attractions_id");
        //删光表
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("city_chinese", city_chinese);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        db.insert("attractions_id", null, contentValues);

    }

}
